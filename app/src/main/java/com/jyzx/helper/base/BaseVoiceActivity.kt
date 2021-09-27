package com.jyzx.helper.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.jyzx.helper.R
import com.jyzx.helper.utils.ActivityControlUtil
import kotlinx.android.synthetic.main.activity_parent.*

/**
 * 用户名: zcm
 * date: 2021/9/22
 * des: 每个需要用到语音的activity都可以继承该activity
 **/

abstract class BaseVoiceActivit : BaseActivity() {


    override fun beforeView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        beforeView()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.activity_parent)
        addChildView()
        ActivityControlUtil.addActivity(this)
        obtainIntent()
        initNetView()
        initView(savedInstanceState)
        initListener()
        initData()
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {

    }

    override fun initListener() {

    }

    override fun initData() {

    }


    private fun addChildView() {
        //根据id获取view
        var chidView = LayoutInflater.from(this).inflate(layoutId,flParent,false)
        flParent.addView(chidView,0)
    }
}