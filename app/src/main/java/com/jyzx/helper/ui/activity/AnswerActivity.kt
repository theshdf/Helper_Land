package com.jyzx.helper.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.ui.adapter.AnswerFragmentAdapter
import com.jyzx.helper.ui.adapter.ToAnswerFragmentAdapter
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_answer.*

/**
 * 用户名: zcm
 * date: 2021/8/30
 * des:
 **/
class AnswerActivity : BaseActivity(){

    private lateinit var answerFragmentAdapter: ToAnswerFragmentAdapter
    private lateinit var loadingHelper: LoadingHelper

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_answer
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        loadingHelper = ToolbarUtils.setCommonToolbar(this,getString(R.string.iwantanswer),
            { RxToast.info("发布内容啦") },"发布")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initListener() {
        btnPic.setOnClickListener{
            viewPager.currentItem = 0
        }
        btnVoice.setOnClickListener{
            viewPager.currentItem = 1
        }
        btnVideo.setOnClickListener{
            viewPager.currentItem = 2
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                updateStyle(position)
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        loadingHelper.setOnReloadListener {
            loadingHelper.showLoadingView()
            Handler().postDelayed({
                loadingHelper.showContentView()
            },3000)
        }
    }

    override fun initData() {

        answerFragmentAdapter = ToAnswerFragmentAdapter(supportFragmentManager)
        viewPager.adapter = answerFragmentAdapter
        loadingHelper.showLoadingView()
        Handler().postDelayed({
            loadingHelper.showErrorView()
        },3000)
    }

    /**
     * 修改样式
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun  updateStyle(pos: Int){
        when(pos){
            0->{
                btnPic.background =getDrawable (R.drawable.answer_check_sp)
                btnPic.setTextColor(Color.WHITE)
                btnVoice.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnVideo.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnVoice.setTextColor(getColor(R.color.text_black_333))
                btnVideo.setTextColor(getColor(R.color.text_black_333))
            }
            1->{
                btnVoice.background =getDrawable (R.drawable.answer_check_sp)
                btnVoice.setTextColor(getColor(R.color.white))
                btnVideo.setTextColor(getColor(R.color.text_black_333))
                btnPic.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnVideo.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnPic.setTextColor(getColor(R.color.text_black_333))
            }
            2->{
                btnVideo.background =getDrawable (R.drawable.answer_check_sp)
                btnVideo.setTextColor(Color.WHITE)
                btnPic.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnVoice.background =getDrawable (R.drawable.answer_uncheck_sp)
                btnPic.setTextColor(getColor(R.color.text_black_333))
                btnVoice.setTextColor(getColor(R.color.text_black_333))
            }
        }
    }
}