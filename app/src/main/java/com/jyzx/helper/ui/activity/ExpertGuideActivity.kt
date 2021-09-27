package com.jyzx.helper.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.httpsender.kt.errorCode
import com.example.httpsender.kt.errorMsg
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.*
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.ui.adapter.ExperRelaAdapter
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.jyzx.helper.view.SpaceItemDecoration
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_expertguide.*
import kotlinx.coroutines.*
import rxhttp.*
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 用户名: zcm
 * date: 2021/8/25
 * des: 专家指导页面
 **/
class ExpertGuideActivity : BaseActivity(){

    private lateinit var experRelaAdapter: ExperRelaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var datas: ArrayList<Course>
    private  var userId: Int = 0

    override fun beforeView() {
        StatusUtils.updateActivityStatusByColor(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_expertguide
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        userId = intent.getIntExtra("userId",0)
        datas = ArrayList()
        experRelaAdapter = ExperRelaAdapter(R.layout.exper_class_item,datas,this)
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvVideo.layoutManager = linearLayoutManager
        rvVideo.adapter = experRelaAdapter
     }

    override fun initListener() {
        ivBack.setOnClickListener{
            finish()
        }
        btnOrder.setOnClickListener{
            it.onSingleClick{
                RxToast.info("暂无开放该功能")
            }
        }
        experRelaAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick{
                MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    datas[position].courseUrl,
                    datas[position].courseName
                )
            }
        }
    }
    override fun initData() {
        getTeahcer()
    }

    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume()
    }

    override fun onPause() {
        super.onPause()
        //清空缓存
        JZUtils.clearSavedProgress(applicationContext,null)
        Jzvd.releaseAllVideos()
    }

    fun getTeahcer() {
        lifecycleScope.launch {
            RxHttp.postForm(Apiservice.TEACHERE_DETAIL)
                .add("id", userId)
                .connectTimeout(10000)
                .toResponse<TeacherDetailBean>()
                .awaitResult {
                    refreshUi(it)
                }.onFailure {
                    RxToast.showToast( it.errorMsg)
                }
        }
    }

    private fun refreshUi(it : TeacherDetailBean){
        datas.clear()
        if(it.courseList!=null){
            datas.addAll(it.courseList)
            experRelaAdapter.notifyDataSetChanged()
        }
        tvName.text = it.teacherVo.teacherName
        tvRank.text = it.teacherVo.workgroup + "    " + it.teacherVo.level
        tvExpertDetail.text = it.teacherVo.teachingExp
//        jzVideo.setUp(expertObj.videoUrl, expertObj.title, Jzvd.SCREEN_NORMAL)
//        jzVideo.posterImageView.setImageResource(expertObj.videoCover)
        Glide.with(this).load(it.teacherVo.head).error(R.mipmap.ic_launcher).apply(RequestOptions().circleCrop()) .into(ivExpertHead)
    }
}