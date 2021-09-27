package com.jyzx.helper.view

import android.content.Context
import android.util.AttributeSet
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import java.util.jar.Attributes

class MyFullPlay: JzvdStd {

    constructor(context: Context) : super(context) {}
    constructor(context: Context,attributes: AttributeSet): super(context,attributes){}

    override fun setScreenFullscreen() {
        super.setScreenFullscreen()
        backButton.visibility = INVISIBLE
    }
}