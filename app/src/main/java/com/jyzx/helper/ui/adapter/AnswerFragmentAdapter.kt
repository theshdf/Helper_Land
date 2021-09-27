package com.jyzx.helper.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jyzx.helper.ui.fragment.qaFragment.*

/**
user: Administrator
date:2021/8/4
EXP:
 */

open class AnswerFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                AnswerVideoFragment::class.java.newInstance()
            }
            1 -> {
                AnswerVideoFragment::class.java.newInstance()
            }
            2 -> {
                AnswerVideoFragment::class.java.newInstance()
            }
            else -> {
                AnswerVideoFragment::class.java.newInstance()
            }
        }
    }
}