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
import com.jyzx.helper.ui.fragment.qaFragment.AnswerPicFragment
import com.jyzx.helper.ui.fragment.qaFragment.AnswerVideoFragment
import com.jyzx.helper.ui.fragment.qaFragment.AnswerVoiceFragment
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.ToolbarUtils
import kotlinx.android.synthetic.main.activity_solve_method.*

/**
 * 用户名: zcm
 * date: 2021/9/1
 * des:
 **/
class SolveMethodActivtiy:BaseActivity() {

    private lateinit var answerFragmentAdapter: AnswerFragmentAdapter
    private lateinit var loadingHelper: LoadingHelper
    private lateinit var answerVideoFragment: AnswerVideoFragment
    private lateinit var answerPicFragment: AnswerPicFragment
    private lateinit var answerVoiceFragment: AnswerVoiceFragment
    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_solve_method
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        loadingHelper = ToolbarUtils.setCommonToolbar(this,"解决方案")
        answerVideoFragment = AnswerVideoFragment()
        answerPicFragment = AnswerPicFragment()
        answerVoiceFragment = AnswerVoiceFragment()
        if(saveInstanceState == null){
            //第一次打开activity
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content,answerPicFragment)
                .add(R.id.content,answerVoiceFragment)
                .add(R.id.content,answerVideoFragment)
                .hide(answerVoiceFragment)
                .hide(answerVideoFragment)
                .show(answerPicFragment)
                .commit()
        }
        else{
            answerPicFragment  = supportFragmentManager.findFragmentByTag(answerPicFragment.javaClass.name) as AnswerPicFragment
            answerVoiceFragment  = supportFragmentManager.findFragmentByTag(answerVoiceFragment.javaClass.name) as AnswerVoiceFragment
            answerVideoFragment  = supportFragmentManager.findFragmentByTag(answerVideoFragment.javaClass.name) as AnswerVideoFragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content,answerPicFragment)
                .add(R.id.content,answerVoiceFragment)
                .add(R.id.content,answerVideoFragment)
                .hide(answerVoiceFragment)
                .hide(answerVideoFragment)
                .show(answerPicFragment)
                .commit()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initListener() {
        tvAnswer.setOnClickListener{
            updateStyle(0)
        }
        tvPicAnswer.setOnClickListener{
            updateStyle(1)
        }
        tvVideoAnswer.setOnClickListener{
            updateStyle(2)
        }
    }

    override fun initData() {
        answerFragmentAdapter = AnswerFragmentAdapter(supportFragmentManager)
    }

    /**
     * 修改样式
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun  updateStyle(pos: Int){
        var beginTra = supportFragmentManager.beginTransaction()
        when(pos){
            0->{
                tvAnswer.setTextColor(getColor(R.color.textgreen_1aa))
                tvPicAnswer.setTextColor(getColor(R.color.text_black_333))
                tvVideoAnswer.setTextColor(getColor(R.color.text_black_333))
                beginTra.hide(answerVideoFragment).hide(answerVoiceFragment)
                if(answerPicFragment.isAdded){
                    beginTra.show(answerPicFragment).commit()
                }
                else{
                    beginTra.add(R.id.content,answerPicFragment).commit()
                }
            }
            1->{
                tvPicAnswer.setTextColor(getColor(R.color.textgreen_1aa))
                tvVideoAnswer.setTextColor(getColor(R.color.text_black_333))
                tvAnswer.setTextColor(getColor(R.color.text_black_333))
                beginTra.hide(answerPicFragment).hide(answerVideoFragment)
                if(answerVoiceFragment.isAdded){
                    beginTra.show(answerVoiceFragment).commit()
                }
                else{
                    beginTra.add(R.id.content,answerVoiceFragment).commit()
                }
            }
            2->{
                tvVideoAnswer.setTextColor(getColor(R.color.textgreen_1aa))
                tvAnswer.setTextColor(getColor(R.color.text_black_333))
                tvPicAnswer.setTextColor(getColor(R.color.text_black_333))
                beginTra.hide(answerPicFragment).hide(answerVoiceFragment)
                if(answerVideoFragment.isAdded){
                    beginTra.show(answerVideoFragment).commit()
                }
                else{
                    beginTra.add(R.id.content,answerVideoFragment).commit()
                }
            }
        }
    }
}