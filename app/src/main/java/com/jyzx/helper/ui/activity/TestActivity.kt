package com.jyzx.helper.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig

/**
user: Administrator
date:2021/8/6
EXP:
 */

class TestActivity :BaseActivity() {
    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        ToolbarUtils.setCommonToolbar(this,"智能导学")
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}