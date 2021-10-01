package com.jyzx.helper.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
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
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.activity_play_rea.*
import kotlinx.android.synthetic.main.common_head.*
import retrofit2.http.Url

/**
user: Administrator
date:2021/8/4
EXP: 播放相关视频页面
 */

class PlayWithRelaActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var videoRelaAdapter: VideoRelaAdapter
    private lateinit var videos : ArrayList<VideoBean>

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
        videoRelaAdapter.setOnItemClickListener { _, view, position ->
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
            showVideolist()
        }
        ivHideVideoList.setOnClickListener{
            hideVideoList()
        }

        relVideoPlayer.backButton.setOnClickListener{
            onBackPressed()
        }
        relVideoPlayer.setVideoAllCallBack(object : GSYSampleCallBack(){

            override fun onStartPrepared(url: String?, vararg objects: Any?) {
                super.onStartPrepared(url, *objects)
                Handler().postDelayed(object: Runnable{
                    override fun run() {
                        if(rlVideoList.visibility == View.VISIBLE){
                            hideVideoList()
                        }
                    }
                },3000)
            }
            override fun onClickBlank(url: String?, vararg objects: Any?) {
                super.onClickBlank(url, *objects)
                if(rlVideoList.visibility == View.GONE){
                    showVideolist()
                }
                else{
                    hideVideoList()
                }
            }
        })

    }

    override fun initData() {
        playOnlienVideo(videos[0])
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
        relVideoPlayer.onVideoResume()
    }

    override fun onPause() {
        super.onPause()
        relVideoPlayer.onVideoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    private fun playOnlienVideo(videoBean: VideoBean){
        relVideoPlayer.setUp(videoBean.videoUrl,true,videoBean.title)
        if(videoBean.startPosition == 0L)
        else
            relVideoPlayer.seekOnStart = videoBean.startPosition
        relVideoPlayer.startPlayLogic()

    }

    override fun onBackPressed() {
        relVideoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

    /**
     * 暂停视频的播放
     */
    fun pauseVideo(){
        if(relVideoPlayer!= null){
            relVideoPlayer.onVideoPause()
        }
    }

    /**
     * 播放视频
     */
    fun playVideo(){
        if(relVideoPlayer!= null){
            relVideoPlayer.onVideoResume()
        }
    }

    /**
     * 显示视频列表
     */
    private fun showVideolist(){
        rlVideoList.visibility = View.VISIBLE
        ivHideVideoList.visibility =View.GONE
        ivShowVideoList.visibility = View.GONE
    }

    /**
     * 隐藏视频列表
     */
    private fun hideVideoList(){
        rlVideoList.visibility = View.GONE
        ivHideVideoList.visibility =View.GONE
        ivShowVideoList.visibility = View.VISIBLE
    }
}