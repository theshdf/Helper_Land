package com.jyzx.helper.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jyzx.helper.ui.fragment.childFragent.*

/**
user: Administrator
date:2021/8/4
EXP:
 */

open class HomeFragmentAdapter(fragmentManager: FragmentManager,var fragments: List<Fragment>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
       return  fragments.size
    }

    override fun getItem(position: Int): Fragment {
        //return fragments[position]
        when(position) {
            0 -> {
                return LearnDangHisFragment ::class.java.newInstance()
            }
            1 -> {
                return CountrySideFragment::class.java.newInstance()
            }
            2 -> {
                return PrimaryLevelGovernanceFragment::class.java.newInstance()
            }
            3 -> {
                return NumberBuildFragment::class.java.newInstance()
            }
            4 -> {
                return EcoCultureFragment::class.java.newInstance()
            }
            5 -> {
                return LawBuildFragment::class.java.newInstance()
            }
            6 -> {
                return EcoBuildFragment::class.java.newInstance()
            }
            7 -> {
                return DangBuildFragment::class.java.newInstance()
            }
            else -> {
                return CountrySideFragment::class.java.newInstance()
            }
        }
    }
}