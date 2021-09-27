package com.jyzx.helper.ui.activity

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JzvdStd
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.event.CommonEvent
import com.jyzx.helper.event.PlayEvent
import com.jyzx.helper.event.VoiceResult
import com.jyzx.helper.services.NetBroadcastReceive
import com.jyzx.helper.services.VoiceService
import com.jyzx.helper.services.WakeService
import com.jyzx.helper.ui.activity.face.FaceEnterActivity
import com.jyzx.helper.ui.fragment.*
import com.jyzx.helper.utils.MediaPlay
import com.jyzx.helper.utils.MediaPlay.Companion.playMedia
import com.jyzx.helper.utils.SpeechUtils
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.StringUtils
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.orhanobut.logger.Logger
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.PermissionX.*
import com.permissionx.guolindev.callback.ExplainReasonCallback
import com.permissionx.guolindev.callback.RequestCallback
import com.permissionx.guolindev.request.ExplainScope
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.tamsiree.rxkit.view.RxToast
import com.tencent.mmkv.MMKV
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parent.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.http.POST

/**
user: Administrator
date:2021/8/3
EXP:助手网首页
 */

class MainActivity : BaseActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var liveFragment: LiveFragment
    private lateinit var qaFragment: QaFragment
    private lateinit var discussFragment: DiscussFragment
    private lateinit var mineFragment: MineFragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var netBroadcastReceive: NetBroadcastReceive

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        homeFragment = HomeFragment()
        liveFragment = LiveFragment()
        qaFragment = QaFragment()
        discussFragment = DiscussFragment()
        mineFragment = MineFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        initFragment()
    }

    override fun initListener() {
        btnGroup.check(R.id.btnHome)
        btnHome.setOnClickListener {
            var beginTran = fragmentManager.beginTransaction()
            beginTran.hide(liveFragment)
                .hide(qaFragment)
                .hide(discussFragment)
                .hide(mineFragment)
            if (!homeFragment.isAdded) {
                beginTran.add(R.id.homeContent, homeFragment).commit()
            } else {
                beginTran.show(homeFragment).commit()
            }
        }
        btnLive.setOnClickListener {
            var beginTran = fragmentManager.beginTransaction()
            beginTran.hide(homeFragment)
                .hide(qaFragment)
                .hide(discussFragment)
                .hide(mineFragment)
            if (!liveFragment.isAdded) {
                beginTran.add(R.id.homeContent, liveFragment).commit()
            } else {
                beginTran.show(liveFragment).commit()
            }
        }
        btnQa.setOnClickListener {
            var beginTran = fragmentManager.beginTransaction()
            beginTran.hide(homeFragment)
                .hide(liveFragment)
                .hide(discussFragment)
                .hide(mineFragment)
            if (!qaFragment.isAdded) {
                beginTran.add(R.id.homeContent, qaFragment).commit()
            } else {
                beginTran.show(qaFragment).commit()
            }
        }
        btnDiscuss.setOnClickListener {
            var beginTran = fragmentManager.beginTransaction()
            beginTran.hide(homeFragment)
                .hide(liveFragment)
                .hide(qaFragment)
                .hide(mineFragment)
            if (!discussFragment.isAdded) {
                beginTran.add(R.id.homeContent, discussFragment).commit()
            } else {
                beginTran.show(discussFragment).commit()
            }
        }
        btnMine.setOnClickListener {
            var beginTran = fragmentManager.beginTransaction()
            beginTran.hide(homeFragment)
                .hide(liveFragment)
                .hide(qaFragment)
                .hide(discussFragment)
            if (!discussFragment.isAdded) {
                beginTran.add(R.id.homeContent, mineFragment).commit()
            } else {
                beginTran.show(mineFragment).commit()
            }
        }
        etSearch.setOnClickListener {
            //开启语音
            it.onSingleClick {
                var voiceIntent = Intent(this, VoiceService::class.java)
                startService(voiceIntent)
            }
        }
    }

    override fun initData() {
        registerBroadcast()
        initPermissions()
        getDis()
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        //如果点击了当前的界面 则清空当前界面的语音识别的提醒
        tvVoiceContent.visibility = View.GONE
        rlContent.visibility = View.GONE
        tvVoiceContent.text = ""
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregister()
    }

    /**
     * 授权语音权限、悬浮窗
     */
    private fun initPermissions() {
        var targetSdk = Build.VERSION.SDK_INT
        var permissions: List<String>
        var showContent: String
        when {
            targetSdk > 28 -> {
                permissions = listOf(Manifest.permission.RECORD_AUDIO)
                showContent = "是否获取录音和弹窗权限"
                init(this)
                    .permissions(permissions)
                    .onExplainRequestReason { scope, deniedList ->
                        scope.showRequestReasonDialog(
                            deniedList,
                            "语音权限",
                            "yes",
                            "no"
                        )
                    }
                    .request { allGranted, _, _ ->
                        if (allGranted) {
                            startService()
                        } else {
                            RxToast.error("授予权限失败")
                        }
                    }
                init(this)
                    .permissions(Manifest.permission.SYSTEM_ALERT_WINDOW)
                    .onExplainRequestReason { scope, deniedList ->
                        scope.showRequestReasonDialog(
                            deniedList,
                            "弹框权限",
                            "yes",
                            "no"
                        )
                    }
                    .request { allGranted, _, _ ->
                        if (allGranted) {
                            startService()
                        } else {
                            RxToast.error("授予权限失败")
                        }
                    }
            }
            else -> {
                permissions = listOf(Manifest.permission.RECORD_AUDIO)
                showContent = "是否获取录音限"
                init(this)
                    .permissions(permissions)
                    .onExplainRequestReason { scope, deniedList ->
                        scope.showRequestReasonDialog(
                            deniedList,
                            "语音权限",
                            "yes",
                            "no"
                        )
                    }
                    .request { allGranted, _, _ ->
                        if (allGranted) {
                            startService()
                        } else {
                            RxToast.error("授予权限失败")
                        }
                    }
            }
        }
    }

    /**
     * 启动唤醒服务
     */
    private fun startService() {
        var mWakerIntent = Intent(this, WakeService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(mWakerIntent)
        } else
            startService(mWakerIntent)
    }

    /**
     * 初始化frgament
     */
    private fun initFragment() {
        fragmentTransaction
            .add(R.id.homeContent, homeFragment, homeFragment.javaClass.name)
            .add(R.id.homeContent, liveFragment, liveFragment.javaClass.name)
            .add(R.id.homeContent, qaFragment, qaFragment::javaClass.name)
            .add(R.id.homeContent, discussFragment, discussFragment::javaClass.name)
            .add(R.id.homeContent, mineFragment, mineFragment::javaClass.name)
            .hide(liveFragment).hide(qaFragment).hide(discussFragment).hide(mineFragment).commit()
    }


    /**
     * 处理语音识别出来的中间文字和最终结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handlerVoiceResult(result: VoiceResult) {
        var restultStr = StringUtils.getAllWord(result.result)
        var type = result.type
        showVoiceTip(restultStr)
        if (type == Constants.VOICE_RESULT) {
            Logger.d(Constants.TAG,restultStr)
            Handler().postDelayed({
                //拿到最终结果
                hideVoiceTip()
                etSearch.setText(restultStr)
                var intent = Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra("result", restultStr)
                startActivity(intent)
            }, 1000)
        } else {
            Logger.d(Constants.TAG, "inn$restultStr")
        }
    }

    /**
     * 控制语音的开始和关闭的ui
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleVoice(event: CommonEvent) {
        when (event.type) {
            "onAsrExit" -> {
             //  hideVoiceTip()
                Logger.d(Constants.TAG,"onAsrExit")
            }
            "onAsrReady" -> {
              showVoiceTip("可以说话了")
                Logger.d(Constants.TAG,"onAsrReady")
            }
        }
    }

    private fun getDis(){
        var dis = resources.displayMetrics
        Log.d("VoiceApp",dis.widthPixels.toString())
        Log.d("VoiceApp",dis.heightPixels.toString())
    }

    /**
     * 注册广播
     */
    private fun registerBroadcast(){
        netBroadcastReceive = NetBroadcastReceive()
        var  filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(netBroadcastReceive,filter)
    }

    private fun unregister(){
        unregisterReceiver(netBroadcastReceive)
    }
}