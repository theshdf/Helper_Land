package com.jyzx.helper.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cn.jzvd.JzvdStd
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.*
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.ui.adapter.BookAdapter
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.jyzx.helper.view.GridItemDecoration
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_expertlist.*
import kotlinx.android.synthetic.main.activity_lesson.*
import kotlinx.coroutines.launch
import rxhttp.awaitResult
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 用户名: zcm
 * date: 2021/8/30
 * des: 课程库界面
 **/
class BookActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var bookData: ArrayList<BookBean>
    private lateinit var bookAdapter: BookAdapter
    private var currentIndex: Int = 1//当前加载数据页码
    private var totalCount = 0

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_book
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        ToolbarUtils.setCommonToolbar(this, "我的图书")
        bookData = ArrayList()
        linearLayoutManager = GridLayoutManager(this, 5)
        bookAdapter = BookAdapter(R.layout.book_item, bookData, this)
    }

    override fun initListener() {
        bookAdapter.setOnItemClickListener { _, view, _ ->
            view?.determineTriggerSingleClick {
               /* MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    "http://jykt.jy365.net/lessionnew/mp4/DSP21090301.mp4",
                    "延安精神永放光芒"
                )*/
            }
        }
        srlBook.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                //执行刷新数据
                currentIndex = 1
                getBookList()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                //加载更多的数据
                currentIndex++
                getBookList()
            }

        })
        errorView.setOnClickListener {
            //刷新
            srlBook.autoRefresh()
        }
        emptyView.setOnClickListener {
            srlBook.autoRefresh()
        }
    }

    override fun initData() {
        rvBook.addItemDecoration(GridItemDecoration(40))
        rvBook.layoutManager = linearLayoutManager
        rvBook.adapter = bookAdapter
        srlBook.setEnableAutoLoadMore(false)//不开启自动加载
        srlBook.autoRefresh()
    }

    /**
     * 获取课程库列表
     */
    fun getBookList() {
        lifecycleScope.launch {
            RxHttp.postForm(Apiservice.BOOK_LIST)
                .add("page", currentIndex)
                .add("limit", Constants.PAGECOUNT)
                .toClass<ResponseBean<BookListBean>>()
                .awaitResult {
                    totalCount = it.data.totalCount
                    when (currentIndex) {
                        1 -> handleRefresh(it.data.bookList)
                        else -> handleLoadMore(it.data.bookList)
                    }
                }.onFailure {
                    //取消下拉刷新和上拉加载
                    bookAdapter.setEmptyView(errorView)
                    if (currentIndex == 1) {
                        srlBook.finishRefresh()
                    } else
                        srlBook.finishLoadMore()
                }
        }
    }

    /**
     * 处理下拉刷新
     */
    private fun handleRefresh(data: ArrayList<BookBean>) {
        //有数据的第一页
        if (data != null && data.size > 0) {
            bookData.clear()
            bookData.addAll(data)
            bookAdapter.notifyDataSetChanged()
            when (bookAdapter.itemCount) {
                totalCount -> {
                    //如果刷新拿到了全部数据
                    srlBook.finishRefreshWithNoMoreData()
                }
                Constants.PAGECOUNT -> {
                    srlBook.setEnableLoadMore(true)
                }
                else -> {
                    srlBook.finishRefreshWithNoMoreData()
                }
            }
        }
        //没有数据的情况
        else {
            bookAdapter.setEmptyView(emptyView)
            //如果第一页没有数据，上拉加载要禁止
            srlBook.setEnableLoadMore(true)
        }
        //停止刷新
        srlBook.finishRefresh()
    }

    /**
     * 处理上拉刷新
     */
    private fun handleLoadMore(data: ArrayList<BookBean>) {
        if (data != null) {
            if (bookAdapter.itemCount == totalCount) {
                //全部加载完毕
                srlBook.finishLoadMoreWithNoMoreData()
            } else {
                //数据不是最后一页的情况
                bookData.addAll(data)
                bookAdapter.notifyDataSetChanged()
                srlBook.finishLoadMore()
            }
        }
    }
}
