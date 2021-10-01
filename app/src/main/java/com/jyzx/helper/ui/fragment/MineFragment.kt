package com.jyzx.helper.ui.fragment

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jyzx.helper.Constants
import com.jyzx.helper.HelperApplication
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment
import com.jyzx.helper.ui.activity.*
import com.jyzx.helper.utils.GsonUtil
import com.tamsiree.rxkit.view.RxToast
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
            if(!HelperApplication.isLogin){
                RxToast.showToast("请先登录")
                return@setOnClickListener
            }
            startActivity(Intent(activity,TestActivity::class.java))
        }
        tvUserName.setOnClickListener {
            if(tvUserName.text.equals("暂未登录")){
                startActivity(Intent(activity,LoginActivity::class.java))
            }
        }
        loginOut.setOnClickListener {
            //退出登录
            tvUserName.text = "暂未登录"
            loginOut.visibility = View.GONE
            HelperApplication.isLogin = false
            refreshPage()
        }
    }

    override fun initData() {
        var option = RequestOptions()
        option.circleCrop()
        Glide.with(this).load(R.mipmap.head).apply(option).into(ivHeadIcon)
        rl_answer.setOnClickListener{
            //我的专家
            if(!HelperApplication.isLogin){
                RxToast.showToast("请先登录")
                return@setOnClickListener
            }
            var intent = Intent(activity,ExpertListActivity::class.java)
            startActivity(intent)
        }
        rl_comment.setOnClickListener{
            //我的图书
            if(!HelperApplication.isLogin){
                RxToast.showToast("请先登录")
                return@setOnClickListener
            }
            var intent = Intent(activity,BookActivity::class.java)
            startActivity(intent)
        }
        rl_class.setOnClickListener{
            if(!HelperApplication.isLogin){
                RxToast.showToast("请先登录")
                return@setOnClickListener
            }
            var intent = Intent(activity,LessonActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //如果登录页面关闭 则刷新当前页面
        refreshPage()

        //用户是否登录
    }

    /**
     * 刷新当前页面
     */
    private fun refreshPage(){
        var mmkv = MMKV.defaultMMKV()
        var userjson = mmkv.decodeString(Constants.USERINFO)
        if(HelperApplication.isLogin) {
            /*if (!TextUtils.isEmpty(userjson)) {
                var userinfo = GsonUtil.getObjFromJson(userjson!!)
                userinfo?.let {
                    tvUserName.text = userinfo.Username
                }*/
                    var userinfo = HelperApplication.currentUser
                tvUserName.text = userinfo.Username
                loginOut.visibility = View.VISIBLE
                //根据用户名字修改
                if (userinfo.Username == "张昌明") {
                    tvMineQuesCount.text = "20"
                    tvMineAnsCount.text = "18"
                    tvMineRepCount.text = "44"
                    rl_study.setBackgroundResource(R.mipmap.zndx_1)
                    class1.setImageResource(R.mipmap.dagnhis1)
                    class2.setImageResource(R.mipmap.dagnhis3)
                    class3.visibility = View.GONE
                    book1.setImageResource(R.mipmap.jgsjs)
                    book2.setImageResource(R.mipmap.yasqyyajsyj)
                    teacher1.setImageResource(R.mipmap.ljp_head)
                    teacher2.setImageResource(R.mipmap.wxg_head)
                } else {
                    tvMineQuesCount.text = "60"
                    tvMineAnsCount.text = "10"
                    tvMineRepCount.text = "7"
                    rl_study.setBackgroundResource(R.mipmap.zndx_1)
                    class1.setImageResource(R.mipmap.jinggangshan1)
                    class2.setImageResource(R.mipmap.jinggangshan2)
                    class3.visibility = View.GONE
                    book1.setImageResource(R.mipmap.lgcdydxy)
                    book2.setImageResource(R.mipmap.dgmhdcdx)
                    teacher1.setImageResource(R.mipmap.azh_head)
                    teacher2.setImageResource(R.mipmap.zhuyu_head)
                }
            var option = RequestOptions()
            option.circleCrop()
            if(!TextUtils.isEmpty(userinfo.UserPhoto)){
                Glide.with(this).load(userinfo.UserPhoto).apply(option).into(ivHeadIcon)
            }
            else
                Glide.with(this).load(R.mipmap.head).apply(option).into(ivHeadIcon)
        }
        else{
            tvUserName.text = "暂未登录"
            tvMineQuesCount.text = "0"
            tvMineAnsCount.text = "0"
            tvMineRepCount.text = "0"
            loginOut.visibility = View.GONE
            class1.visibility =View.VISIBLE
            class2.visibility =View.VISIBLE
            class3.visibility =View.VISIBLE
            class1.setImageResource(R.mipmap.ic_empty)
            class2.setImageResource(R.mipmap.ic_empty)
            class3.setImageResource(R.mipmap.ic_empty)
            book1.setImageResource(R.mipmap.ic_empty)
            book2.setImageResource(R.mipmap.ic_empty)
            teacher1.setImageResource(R.mipmap.ic_empty)
            teacher2.setImageResource(R.mipmap.ic_empty)
            var option = RequestOptions()
            option.circleCrop()
            Glide.with(this).load(R.mipmap.head).apply(option).into(ivHeadIcon)
        }
    }

    private fun isLogin(){
        if(!HelperApplication.isLogin){
            RxToast.showToast("请先登录")
            return
        }
    }

    fun  openClass(){
        //我的课程
        if(!HelperApplication.isLogin){
            RxToast.showToast("请先登录")
            return
        }
        var intent = Intent(activity,LessonActivity::class.java)
        startActivity(intent)
    }

    fun openBook(){
        if(!HelperApplication.isLogin){
            RxToast.showToast("请先登录")
            return
        }
        var intent = Intent(activity,BookActivity::class.java)
        startActivity(intent)
    }

    fun openTeacher(){
        if(!HelperApplication.isLogin){
            RxToast.showToast("请先登录")
            return
        }
        var intent = Intent(activity,ExpertListActivity::class.java)
        startActivity(intent)
    }
}