package com.jyzx.helper.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cn.jzvd.JzvdStd
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.*
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.ui.adapter.ExpertAdaper
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.jyzx.helper.view.GridItemDecoration
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_expertlist.*
import kotlinx.android.synthetic.main.activity_lesson.*
import kotlinx.android.synthetic.main.layout_jzstd_notitle.*
import kotlinx.coroutines.launch
import rxhttp.awaitResult
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp

/**
 * 用户名: zcm
 * date: 2021/8/30
 * des: 专家列表页
 **/
class ExpertListActivity : BaseActivity(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var expertDatas: ArrayList<TeacherBean>
    private lateinit var expertAdaper: ExpertAdaper
    private var currentIndex: Int = 1//当前加载数据页码
    private var totalCount = 0

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_expertlist
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        ToolbarUtils.setCommonToolbar(this,"我的专家")
        expertDatas = ArrayList()
        linearLayoutManager = LinearLayoutManager(this)
        expertAdaper = ExpertAdaper(this,R.layout.expert_item,expertDatas)
    }

    override fun initListener() {
        expertAdaper.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick{
               /* MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    "http://jykt.jy365.net/lessionnew/mp4/DSP21090301.mp4",
                    "延安精神永放光芒"
                )*/
                //跳转到专家详情页面
                var intent = Intent(this,ExpertGuideActivity::class.java)
                //设置专家id
                intent.putExtra("userId",expertDatas[position].id)
                startActivity(intent)
            }
        }

        srlExpert.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                //执行刷新数据
                currentIndex = 1
                getExpertList()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                //加载更多的数据
                currentIndex++
                getExpertList()
            }

        })
        errorView.setOnClickListener {
            //刷新
            srlExpert.autoRefresh()
        }
        emptyView.setOnClickListener {
            srlExpert.autoRefresh()
        }
    }

    override fun initData() {
        rvExpertList.addItemDecoration(GridItemDecoration(40))
        rvExpertList.layoutManager = linearLayoutManager
        rvExpertList.adapter = expertAdaper
        srlExpert.autoRefresh()
    }


    /**
     * 获取课程库列表
     */
    fun getExpertList() {
        lifecycleScope.launch {
            RxHttp.postForm(Apiservice.TEACHER_LIST)
                .add("page", currentIndex)
                .add("limit", Constants.PAGECOUNT)
                .toClass<ResponseBean<TeacherListBean>>()
                .awaitResult {
                    totalCount = it.data.totalCount
                    when (currentIndex) {
                        1 -> handleRefresh(it.data.teacherResourceList)
                        else -> handleLoadMore(it.data.teacherResourceList)
                    }
                }.onFailure {
                    //取消下拉刷新和上拉加载
                    expertAdaper.setEmptyView(errorView)
                    if (currentIndex == 1) {
                        srlExpert.finishRefresh()
                    } else
                        srlExpert.finishLoadMore()
                }
        }
    }

    /**
     * 处理下拉刷新
     */
    private fun handleRefresh(data: ArrayList<TeacherBean>) {
        //有数据的第一页
        if (data != null && data.size > 0) {
            expertDatas.clear()
            expertDatas.addAll(data)
            expertAdaper.notifyDataSetChanged()
            when (expertAdaper.itemCount) {
                totalCount -> {
                    //如果刷新拿到了全部数据
                    srlExpert.finishRefreshWithNoMoreData()
                }
                Constants.PAGECOUNT -> {
                    srlExpert.setEnableLoadMore(true)
                }
                else -> {
                    srlExpert.finishRefreshWithNoMoreData()
                }
            }
        }
        //没有数据的情况
        else {
            expertAdaper.setEmptyView(emptyView)
            //如果第一页没有数据，上拉加载要禁止
            srlExpert.setEnableLoadMore(true)
        }
        //停止刷新
        srlExpert.finishRefresh()
    }

    /**
     * 处理上拉刷新
     */
    private fun handleLoadMore(data: ArrayList<TeacherBean>) {
        if (data != null) {
            if (expertAdaper.itemCount == totalCount) {
                //全部加载完毕
                srlExpert.finishLoadMoreWithNoMoreData()
            } else {
                //数据不是最后一页的情况
                expertDatas.addAll(data)
                expertAdaper.notifyDataSetChanged()
                srlExpert.finishLoadMore()
            }
        }
    }

}