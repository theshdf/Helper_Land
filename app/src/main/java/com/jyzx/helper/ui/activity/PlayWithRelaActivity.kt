package com.jyzx.helper.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JZDataSource
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.base.JZMediaSystemAssertFolder
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.ui.adapter.VideoRelaAdapter
import com.jyzx.helper.utils.FakeDataUtil
import kotlinx.android.synthetic.main.activity_play_rea.*
import kotlinx.android.synthetic.main.common_head.*
import retrofit2.http.Url

/**
user: Administrator
date:2021/8/4
EXP: 播放相关视频页面
 */

class PlayWithRelaActivity : BaseActivity() {
    private lateinit var videoUrl: String
    private lateinit var videoTitle :String
    private lateinit var map: LinkedHashMap<String,String>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var videoRelaAdapter: VideoRelaAdapter
    private lateinit var videos : ArrayList<VideoBean>
    private lateinit var URL:String

    override fun beforeView() {
        JZUtils.hideStatusBar(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_play_rea
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        videos = ArrayList()
        val result =  intent.getStringExtra("result")
        var videoList = FakeDataUtil.initTeacherOrClassByType(result)
        videoList[0].isPlay = true
        videos.addAll(videoList)
        linearLayoutManager = LinearLayoutManager(this)
        videoRelaAdapter = VideoRelaAdapter(R.layout.video_rela_item,videos,this)
        rvVideo.adapter = videoRelaAdapter
        rvVideo.layoutManager = linearLayoutManager
    }

    override fun initListener() {
        ivBack.setOnClickListener {
            it.onSingleClick{
                jzRela.clearFloatScreen()
                finish()
            }
        }
        videoRelaAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                for (i in 0 until videos.size) {
                    videos[i].isPlay = false
                }
                videos[position].isPlay = true
                videoRelaAdapter.notifyDataSetChanged()
                playOnlienVideo(videos[position])
            }
        }
        ivShowVideoList.setOnClickListener{
            rlVideoList.visibility = View.VISIBLE
            ivHideVideoList.visibility =View.VISIBLE
            ivShowVideoList.visibility = View.GONE
        }
        ivHideVideoList.setOnClickListener{
            rlVideoList.visibility = View.GONE
            ivHideVideoList.visibility =View.GONE
            ivShowVideoList.visibility = View.VISIBLE
        }
    }

    override fun initData() {
        playOnlienVideo(videos[0])
        //控制开关按钮的显示
       /* if(videos.size>0){
            ivHideVideoList.visibility = View.VISIBLE
            ivShowVideoList.visibility = View.GONE
        }
        else{
            ivHideVideoList.visibility = View.GONE
            ivShowVideoList.visibility = View.VISIBLE
        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //cpAssertVideoToLocalPath()
                initData()
            } else {
                finish()
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
    }

    fun playOnlienVideo(videoBean: VideoBean){
        map = LinkedHashMap()
        map["高清"] = videoBean.videoUrl
        map["标清"] =videoBean.videoUrl
        map["普清"] =videoBean.videoUrl
        val jzDataSource = JZDataSource(map,videoBean.title)
        jzDataSource.looping = true
        jzDataSource.currentUrlIndex = 2
        jzDataSource.headerMap["key"] = "value" //header
        jzRela.setUp(jzDataSource,JzvdStd.SCREEN_FULLSCREEN)
        jzRela.startVideo()
    }
}