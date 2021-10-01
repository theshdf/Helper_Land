package com.jyzx.helper.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import cn.jzvd.JZDataSource
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.utils.StatusUtils
import com.permissionx.guolindev.PermissionX
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import kotlinx.android.synthetic.main.activity_play.*
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager

/**
user: Administrator
date:2021/8/4
EXP: 视频播放页面
 */

class PlayActivity : BaseActivity() {

    private lateinit var videoUrl: String
    private lateinit var videoTitle :String
    private lateinit var map: LinkedHashMap<String,String>
    private lateinit var videoBean: VideoBean

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_play
    }

    override fun obtainIntent() {
        videoBean =  intent.getSerializableExtra(Constants.VIDEOBEAN) as VideoBean
        videoUrl= videoBean.videoUrl
        videoTitle = videoBean.title
    }

    override fun initView(saveInstanceState: Bundle?) {

    }

    override fun initListener() {
        videoPlayer.backButton.setOnClickListener{
            onBackPressed()
        }
    }

    override fun initData() {
        videoPlayer.setUp(videoUrl,true,videoTitle)
        seekToPosition(0)
        videoPlayer.startPlayLogic()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initData()
            } else {
                finish()
            }
        }
    }


    /**
     * 播放本地assets视频
     */
    private fun playAssets(){
        //申请读写本地文件权限
        PermissionX.init(this)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList,"需要读写权限","确认","拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
                if(allGranted){
                    //全部授权
                }
                else{
                    //拒绝授权
                }
            }
    }

    override fun onBackPressed() {
        videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    override fun onPause() {
        super.onPause()
        videoPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
    }

    /**
     * 制定位置播放视频
     */
    private fun seekToPosition(position: Int){
        if(position != 0){
            videoPlayer.seekOnStart = position as Long
        }
    }

    /**
     * 暂停视频的播放
     */
     fun pauseVideo(){
        if(videoPlayer!= null){
            videoPlayer.onVideoPause()
        }
    }

    /**
     * 播放视频
     */
    fun playVideo(){
        if(videoPlayer!= null){
            videoPlayer.onVideoResume()
        }
    }
}