package com.jyzx.helper.ui.fragment.qaFragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.ui.adapter.AnswerVideoAdapter
import com.jyzx.helper.utils.FakeDataUtil
import kotlinx.android.synthetic.main.answer_all.*

/**
 * 用户名: zcm
 * date: 2021/8/31
 * des: 图文回答界面
 **/
class AnswerVideoFragment: BaseListFragment() {

    private lateinit var datas: ArrayList<VideoBean>
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var answerVideoAdapter: AnswerVideoAdapter
    private  lateinit var loadingHelper: LoadingHelper

    override fun getContentLayoutId(): Int {
        return R.layout.answer_all
    }

    override fun initView(view: View) {

    }


    override fun initListener(view: View?) {
        var container = view?.findViewById(R.id.container) as View
        loadingHelper = LoadingHelper(container)
      //  loadingHelper.setOnReloadListener(this)
    }

    override fun initData() {
        loadingHelper.showLoadingView()
        datas = ArrayList()
        datas = FakeDataUtil.getCountryVideo()
        answerVideoAdapter = AnswerVideoAdapter(R.layout.solve_video_item,datas,activity)
        gridLayoutManager = GridLayoutManager(activity,2)
        //rvAnswer.addItemDecoration(S)
        rvAnswer.adapter = answerVideoAdapter
        rvAnswer.layoutManager = gridLayoutManager
        Handler().postDelayed({ loadingHelper.showEmptyView() },3000)
    }
}