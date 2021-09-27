package com.jyzx.helper.ui.fragment.qaFragment

import android.os.Handler
import android.view.View
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment

/**
 * 用户名: zcm
 * date: 2021/8/31
 * des: 图文回答界面
 **/
class AnswerVoiceFragment: BaseListFragment() {
    private  lateinit var loadingHelper: LoadingHelper
    override fun getContentLayoutId(): Int {
        return R.layout.answer_all
    }

    override fun initView(view: View) {
        loadingHelper = LoadingHelper(view)
    }

    override fun initListener(view: View?) {

    }

    override fun initData() {
        loadingHelper.showLoadingView()
        Handler().postDelayed({ loadingHelper.showContentView() },3000)
    }
}