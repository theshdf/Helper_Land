package com.jyzx.helper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.TextureView
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.example.httpsender.kt.errorMsg
import com.jyzx.helper.Constants
import com.jyzx.helper.HelperApplication
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.base.BaseWebView
import com.jyzx.helper.bean.ResponseBean
import com.jyzx.helper.bean.ResponseLoginBean
import com.jyzx.helper.bean.UserInfo
import com.jyzx.helper.event.CommonEvent
import com.jyzx.helper.event.VoiceResult
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.utils.GsonUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.StringUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.orhanobut.logger.Logger
import com.tamsiree.rxkit.view.RxToast
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rxhttp.awaitResult
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp

/**
 * 用户名: zcm
 * date: 2021/9/27
 * des: 登录页面
 **/
class LoginActivity :  BaseActivity(){
    private lateinit var mmkv: MMKV
    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        loadingHelper = ToolbarUtils.setCommonToolbar(this,"登录页面")
        //如果之前保存过账号密码，直接取出来显示
        mmkv =  MMKV.defaultMMKV()
        var userJson = mmkv.decodeString(Constants.USERINFO)
        userJson?.let {
          var userinfo = GsonUtil.getObjFromJson(userJson!!)
            userinfo!!.let {
              var account = userinfo.UserAccount
              var pass = mmkv.decodeString(Constants.PASSWORD)
              login_userName_Et.setText(account)
              login_password_Et.setText(pass)
          }
        }
    }

    override fun initListener() {
        login_Btn.setOnClickListener{
            var username = login_userName_Et.text.toString()
            var pass = login_password_Et.text.toString()
            if(TextUtils.isEmpty(username)){
                RxToast.showToast("请输入用户名")
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass)){
                RxToast.showToast("请输入用户密码")
                return@setOnClickListener
            }
            if(!cbPrivacy.isChecked){
               RxToast.showToast("请先阅读隐私政策")
                return@setOnClickListener
            }
            //走接口
            login(username,pass)
        }
        user_privacy_agreement.text = StringUtils.richText("我已阅读并同意《隐私政策》",object : StringUtils.onRichListener{
            override fun onRichBack() {
                startActivity(
                    Intent(this@LoginActivity, BaseWebView::class.java).putExtra(
                        "URL",
                        "http://book.jystudy.com:8080/update/monotouch/jizhun/privacyPolicyApp.html"
                    ).putExtra("Title", "隐私政策")
                )
            }
        })
        user_privacy_agreement.movementMethod = LinkMovementMethod.getInstance();
    }

    override fun initData() {

    }

    /**
     * 用户进行登录
     */
    private fun login(username: String,pass: String){
        loadingHelper.showLoadingView()
        lifecycleScope.launch {
            RxHttp.postJson(Apiservice.LOGIN)
                .add("Account", username)
                .add("Password", pass)
                .toClass<ResponseLoginBean<UserInfo>>()
                .awaitResult { it ->
                    loadingHelper.showContentView()
                    if(it.Type == 1){
                        //登录成功 1. 保存用户登录数据
                            if(cbRemeber .isChecked){
                                //保存用户数据
                                var json =  GsonUtil.getJsonFromObj(it.Data)
                                mmkv.encode(Constants.USERINFO,json)
                                mmkv.encode(Constants.PASSWORD,pass)
                            }
                        HelperApplication.isLogin = true
                        HelperApplication.currentUser = it.Data
                        finish()
                    }
                    else{
                        //提示用户
                            it.Message.let {
                                RxToast.showToast(it)
                            }
                    }
                    loadingHelper.showContentView()
                }.onFailure {
                    loadingHelper.showContentView()
                    RxToast.showToast(it.errorMsg)
                }
        }
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
                handleKey(restultStr)
            }, 0)
        } else {
            Logger.d(Constants.TAG, "inn$restultStr")
        }
    }

    /**
     * 控制语音的开始和关闭的ui
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun handleVoice(event: CommonEvent) {
        when (event.type) {
            "onAsrExit" -> {
                Handler().postDelayed({
                    hideVoiceTip()
                },1000)
                Logger.d(Constants.TAG,"onAsrExit")
            }
            "onAsrReady" -> {
                showVoiceTip("可以说话了")
                Logger.d(Constants.TAG,"onAsrReady")
            }
        }
    }

    private fun handleKey(result:String) {
        if(result.contains("关闭登录") ) {
            finish()
        }
        else if(result.contains("开始登录") ) {
            var username = login_userName_Et.text.toString()
            var pass = login_password_Et.text.toString()
            if(TextUtils.isEmpty(username)){
                RxToast.showToast("请输入用户名")
                return
            }
            if(TextUtils.isEmpty(pass)){
                RxToast.showToast("请输入用户密码")
                return
            }
            if(!cbPrivacy.isChecked){
                RxToast.showToast("请先阅读隐私政策")
                return
            }
            //走接口
            login(username,pass)
        }
    }
}