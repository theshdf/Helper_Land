package com.jyzx.helper.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.ui.activity.PlayActivity
import com.jyzx.helper.ui.adapter.LiveAdapter
import com.jyzx.helper.utils.FakeDataUtil
import kotlinx.android.synthetic.main.fragment_live.*

/**
user: Administrator
date:2021/8/3
EXP:
 */

class LiveFragment : BaseListFragment() {
    private lateinit  var liveAdapter : LiveAdapter
    private lateinit var gridLayoutManager : GridLayoutManager
    private lateinit var liveData: ArrayList<VideoBean>


    override fun getContentLayoutId(): Int {
        return R.layout.fragment_live
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {

    }

    override fun initData() {
        gridLayoutManager = GridLayoutManager(activity,3)
        liveData = FakeDataUtil.getCountryVideo()
        liveAdapter = LiveAdapter(R.layout.live_item,liveData,this)
        rvLive.adapter =liveAdapter
        rvLive.layoutManager = gridLayoutManager
        liveAdapter.setOnItemClickListener { adapter, view, position ->
            view.determineTriggerSingleClick{
                JzvdStd.startFullscreenDirectly(
                    activity,
                    JzvdStd::class.java,
                    liveData[position].videoUrl,
                    liveData[position].title
                )
            }
        }
    }
}