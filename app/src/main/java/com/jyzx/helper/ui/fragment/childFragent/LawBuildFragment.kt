package com.jyzx.helper.ui.fragment.childFragent

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.ui.activity.ExpertGuideFakeActivity
import com.jyzx.helper.ui.activity.PlayActivity
import com.jyzx.helper.ui.adapter.GuideAdapter
import com.jyzx.helper.ui.adapter.SolveAdapter
import com.jyzx.helper.utils.FakeDataUtil
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.fragment_countryside.*
import kotlinx.coroutines.GlobalScope

/**
user: Administrator
date:2021/8/4
EXP:法治建设
 */

class LawBuildFragment : BaseFragment() {

    private  var solveAdapter:SolveAdapter? = null
    private  var guideAdapter: GuideAdapter? = null
    private lateinit var solveDatas: ArrayList<VideoBean>
    private lateinit var guideDatas: ArrayList<VideoBean>
    private  var gridLayoutManager: GridLayoutManager? = null
    private  var linearLayoutManager: LinearLayoutManager? = null
    private  var head: View? = null
    private  var guideHead: View? = null

    override fun getContentLayoutId(): Int {
       return R.layout.fragment_countryside
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {

    }

    override fun initData() {
        initFakeData()
        solveAdapter = SolveAdapter(R.layout.solve_item,solveDatas,activity)
        guideAdapter = GuideAdapter(R.layout.guide_item,guideDatas,activity)
        gridLayoutManager = GridLayoutManager(activity,2)
        gridLayoutManager?.orientation  = LinearLayoutManager.VERTICAL
        linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager?.orientation = LinearLayoutManager.VERTICAL
        solveRv.layoutManager = gridLayoutManager
        solveRv.adapter = solveAdapter
        guideRv.layoutManager = linearLayoutManager
        guideRv.adapter = guideAdapter
        addHeader()
        refresh.setColorSchemeColors(Color.parseColor("#1EBF8C"))
        refresh.setOnRefreshListener {
            refresh.isRefreshing = false
            solveAdapter?.notifyDataSetChanged()
        }
        solveAdapter?.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                var intent = Intent(activity,PlayActivity::class.java)
                intent.putExtra(Constants.VIDEOBEAN,solveDatas[position])
                startActivity(intent)
            }
        }
        guideAdapter?.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                var intent = Intent(activity, ExpertGuideFakeActivity::class.java)
                intent.putExtra("expertObj",guideDatas[position])
                startActivity(intent)
            }
        }
    }

    override fun reload() {

    }

    /**
     *
     */
    fun initFakeData(){
        //solve
        solveDatas = ArrayList()
        guideDatas = ArrayList()
        solveDatas.addAll(FakeDataUtil.getLawCountry())
        guideDatas.addAll(FakeDataUtil.getLawCountry().subList(0,2))
    }

    private fun addHeader(){
        head = LayoutInflater.from(activity).inflate(R.layout.solve_head_view,solveRv,false)
        var tvView = head!!.findViewById<TextView>(R.id.tvQuestion)
        tvView.text = Constants.LAWBUILD_QUESTION
        solveAdapter?.addHeaderView(head!!)
        guideHead = LayoutInflater.from(activity).inflate(R.layout.guide_head_view,guideRv,false)
        guideAdapter?.addHeaderView(guideHead!!)
    }

    override fun onDestroyView() {
        head = null
        guideHead = null
        linearLayoutManager = null
        gridLayoutManager = null
        solveAdapter = null
        guideAdapter = null
        super.onDestroyView()
    }
}