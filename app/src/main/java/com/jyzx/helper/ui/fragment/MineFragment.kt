package com.jyzx.helper.ui.fragment

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment
import com.jyzx.helper.ui.activity.*
import com.jyzx.helper.utils.GsonUtil
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_mine.*

/**
user: Administrator
date:2021/8/3
EXP:
 */

class MineFragment : BaseListFragment() {
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {
        rl_study.setOnClickListener{
            startActivity(Intent(activity,TestActivity::class.java))
        }
        tvUserName.setOnClickListener {
            if(tvUserName.text.equals("暂未登录")){
                startActivity(Intent(activity,LoginActivity::class.java))
            }
        }
    }

    override fun initData() {
        var option = RequestOptions()
        option.circleCrop()
        Glide.with(this).load(R.mipmap.head).apply(option).into(ivHeadIcon)
        rl_answer.setOnClickListener{
            //我的专家
            var intent = Intent(activity,ExpertListActivity::class.java)
            startActivity(intent)

        }
        rl_comment.setOnClickListener{
            //我的图书
            var intent = Intent(activity,BookActivity::class.java)
            startActivity(intent)
        }
        rl_class.setOnClickListener{
            //我的课程
            var intent = Intent(activity,LessonActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //如果登录页面关闭 则刷新当前页面
        var mmkv = MMKV.defaultMMKV()
        var userjson = mmkv.decodeString(Constants.USERINFO)
        if(!TextUtils.isEmpty(userjson)){
            var userinfo = GsonUtil.getObjFromJson(userjson!!)
            userinfo?.let {
                tvUserName.text = userinfo.Username
            }
        }
        else{
            tvUserName.text = "暂未登录"
        }
    }
}