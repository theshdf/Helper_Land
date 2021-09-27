package com.jyzx.helper.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jyzx.helper.ui.fragment.qaFragment.ToAnswerPicFragment
import com.jyzx.helper.ui.fragment.qaFragment.ToAnswerVideoFragment
import com.jyzx.helper.ui.fragment.qaFragment.ToAnswerVoiceFragment

/**
user: Administrator
date:2021/8/4
EXP:
 */

open class ToAnswerFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                ToAnswerPicFragment::class.java.newInstance()
            }
            1 -> {
                ToAnswerVideoFragment::class.java.newInstance()
            }
            2 -> {
                ToAnswerVoiceFragment::class.java.newInstance()
            }
            else -> {
                ToAnswerPicFragment::class.java.newInstance()
            }
        }
    }
}