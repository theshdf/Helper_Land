package com.jyzx.helper.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.Constants
import com.jyzx.helper.HelperApplication
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.bean.HomeTitleAndKeyWord
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.ui.adapter.HomeFragmentAdapter
import com.jyzx.helper.ui.fragment.childFragent.*
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView
import rxhttp.awaitResult
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
user: Administrator
date:2021/8/3
EXP: 首页展示内容
 */

class HomeFragment : BaseFragment() {

    private lateinit var commonNavigator: CommonNavigator
    private lateinit var homeFragmentAdapter: HomeFragmentAdapter
    private lateinit var fragments : ArrayList<Fragment>
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {

    }

    override fun initData() {
        getHomeTitle()
    }

    override fun reload() {
        getHomeTitle()
    }

    /**
     *
     */
    private fun initFragment(titles: ArrayList<String>){
        fragments = ArrayList()
        for (i in titles.indices){
            when(i){
                0->{
                    var countrySideFragment = LearnDangHisFragment()
                    fragments.add(countrySideFragment)
                }
                1->{
                    var countrySideFragment = CountrySideFragment()
                    fragments.add(countrySideFragment)
                }
                2->{
                    var countrySideFragment = PrimaryLevelGovernanceFragment()
                    fragments.add(countrySideFragment)
                }
                3->{
                    var ecoBuildFragment = NumberBuildFragment()
                    fragments.add(ecoBuildFragment)
                }
                4->{
                    var dangBuildFragment = EcoCultureFragment()
                    fragments.add(dangBuildFragment)
                }
                5->{
                    var numberBuildFragment = LawBuildFragment()
                    fragments.add(numberBuildFragment)
                }
                6->{
                    var numberBuildFragment = EcoBuildFragment()
                    fragments.add(numberBuildFragment)
                }
                7->{
                    var numberBuildFragment = DangBuildFragment()
                    fragments.add(numberBuildFragment)
                }
            }
        }
    }

    /**
     * 获取首页标题
     */
    private fun getHomeTitle(){
        loadingHelper.showLoadingView()
        lifecycleScope.launch {
            RxHttp.get(Apiservice.HOME_TITLE)
                .toResponse<HomeTitleAndKeyWord>()
                .awaitResult {
                    //获取首页标题
                    if(it!=null&&it.firstList.size>0)
                    {
                        loadingHelper.showContentView()
                        setView(it.firstList)
                    }
                    else
                    {
                        //没有数据
                        loadingHelper.showEmptyView()
                    }
                    //获取唤醒关键字
                    HelperApplication.keyWord = it.keyword
                }.onFailure {
                    //显示错误界面
                    loadingHelper.showErrorView()
                }
        }
    }

    private fun setView(titles: ArrayList<String>){
        initFragment(titles)
        homeFragmentAdapter = HomeFragmentAdapter(childFragmentManager,fragments)
        viewPager.adapter = homeFragmentAdapter
        commonNavigator = CommonNavigator(activity)
        commonNavigator.adapter = object : CommonNavigatorAdapter(){
            override fun getCount(): Int {
                return titles.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                var titleView = SimplePagerTitleView(context)
                titleView.normalColor =  Color.parseColor("#FFFFFF")
                titleView.selectedColor = Color.parseColor("#ABF641")
                titleView.text = titles[index].toString()
                titleView.textSize = 15.0f
                titleView.setOnClickListener{
                    viewPager.currentItem = index
                }
                return titleView
            }

            override fun getIndicator(context: Context?): IPagerIndicator {
                var linePagerIndicator = LinePagerIndicator(context)
                linePagerIndicator.mode = LinePagerIndicator.MODE_EXACTLY
                linePagerIndicator.setColors(Color.parseColor("#ABF641"))
                return linePagerIndicator
            }
        }
        indicator.navigator = commonNavigator
        ViewPagerHelper.bind(indicator,viewPager)
    }
}