package com.jyzx.helper.base

import android.os.Bundle
import android.text.TextUtils
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jyzx.helper.R
import com.jyzx.helper.utils.ToolbarUtils
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * 用户名: zcm
 * date: 2021/8/27
 * des:
 **/
class BaseWebView : BaseActivity(){

    lateinit var webView: WebView
    private lateinit var loadUrl: String
    private lateinit var title: String
    override fun beforeView() {
        loadUrl = intent.getStringExtra("URL")!!
        title = intent.getStringExtra("Title")!!
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        ToolbarUtils.setCommonToolbar(this,if(TextUtils.isEmpty(title)) "" else title )
    }

    override fun initListener() {

    }


    override fun initData() {
        webView = myWebView
        loadUrl?.let {
            webView.loadUrl(loadUrl)
        }
        var webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true//允许js弹窗
        webSettings.setSupportZoom(true)
        webSettings.displayZoomControls = false//原生的缩放控件
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE//不适用缓存
        webView.webViewClient = object : WebViewClient(){

        }
    }

    /**
     * 和js 交互
     */
    fun dealJavaScript(){
        //调用的方法名字,js返回值
        webView.evaluateJavascript("javavscript:metods()"
        ) {

        }
    }
}