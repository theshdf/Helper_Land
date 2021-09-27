package com.jyzx.helper.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import com.example.httpsender.kt.errorCode
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.Course
import com.jyzx.helper.bean.CourseList
import com.jyzx.helper.bean.ResponseBean
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.ui.adapter.ClassAdapter
import com.jyzx.helper.ui.adapter.ExpertAdaper
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.jyzx.helper.view.GridItemDecoration
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.jyzx.helper.view.SpaceItemDecoration
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_lesson.*
import kotlinx.coroutines.launch
import rxhttp.awaitResult
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp

/**
 * 用户名: zcm
 * date: 2021/8/30
 * des: 课程库界面
 **/
class LessonActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var lessonDatas: ArrayList<Course>
    private lateinit var lessonAdapter: ClassAdapter
    private  var currentIndex : Int = 1//当前加载数据页码
    private var totalCount = 0

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_lesson
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        ToolbarUtils.setCommonToolbar(this,"我的课程")
        lessonDatas = ArrayList()
        lessonDatas = ArrayList()
        linearLayoutManager = GridLayoutManager(this,3)
        lessonAdapter = ClassAdapter(R.layout.class_item_mine,lessonDatas,this)
    }

    override fun initListener() {
        lessonAdapter.setOnItemClickListener { _, view, _ ->
            view?.determineTriggerSingleClick{
           /*     MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    lessonDatas[position].videoUrl,
                    lessonDatas[position].title
                )*/
            }
        }
        srlLesson.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onRefresh(refreshLayout: RefreshLayout) {
                //执行刷新数据
                currentIndex  = 1
                getCourseList()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                //加载更多的数据
                currentIndex++
                getCourseList()
            }

        })
        errorView.setOnClickListener{
            //刷新
            srlLesson.autoRefresh()
        }
        emptyView.setOnClickListener{
            srlLesson.autoRefresh()
        }
    }

    override fun initData() {
        rvLesson.addItemDecoration(GridItemDecoration(40))
        rvLesson.layoutManager = linearLayoutManager
        rvLesson.adapter = lessonAdapter
        srlLesson.setEnableAutoLoadMore(false)//不开启自动加载
        srlLesson.autoRefresh()
    }

    /**
     * 获取课程库列表
     */
    fun getCourseList(){
        lifecycleScope.launch {
            RxHttp.postForm(Apiservice.COURSE_LIST)
                .add("page",currentIndex)
                .add("limit", Constants.PAGECOUNT)
                .toClass<ResponseBean<CourseList>>()
                .awaitResult {
                    totalCount = it.data.totalCount
                    when(currentIndex){
                        1->handleRefresh(it.data.courseList)
                        else->handleLoadMore(it.data.courseList)
                    }
                }.onFailure {
                    //取消下拉刷新和上拉加载
                    lessonAdapter.setEmptyView(errorView)
                    if(currentIndex == 1){
                        srlLesson.finishRefresh()
                    }
                    else
                        srlLesson.finishLoadMore()
                }
        }
    }

    /**
     * 处理下拉刷新
     */
    private fun handleRefresh(data: ArrayList<Course>){
        //有数据的第一页
        if(data!=null&&data.size>0){
            lessonDatas.clear()
            lessonDatas.addAll(data)
            lessonAdapter.notifyDataSetChanged()
            when (lessonAdapter.itemCount) {
                totalCount -> {
                    //如果刷新拿到了全部数据
                    srlLesson.finishRefreshWithNoMoreData()
                }
                Constants.PAGECOUNT -> {
                    srlLesson.setEnableLoadMore(true)
                }
                else -> {
                    srlLesson.finishRefreshWithNoMoreData()
                }
            }
        }
        //没有数据的情况
        else{
            lessonAdapter.setEmptyView(emptyView)
            //如果第一页没有数据，上拉加载要禁止
            srlLesson.setEnableLoadMore(true)
        }
        //停止刷新
        srlLesson.finishRefresh()
    }

    /**
     * 处理上拉刷新
     */
    private fun handleLoadMore(data: ArrayList<Course>){
        if(data!=null){
            if(lessonAdapter.itemCount == totalCount){
                //全部加载完毕
                srlLesson.finishLoadMoreWithNoMoreData()
            }
            else{
                //数据不是最后一页的情况
                lessonDatas.addAll(data)
                lessonAdapter.notifyDataSetChanged()
                srlLesson.finishLoadMore()
            }
        }
    }
}