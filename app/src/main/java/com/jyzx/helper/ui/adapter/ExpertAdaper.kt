package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.TeacherBean
import com.jyzx.helper.bean.VideoBean

/**
 * 用户名: zcm
 * date: 2021/8/30
 * des: 专家列表适配器
 **/
class ExpertAdaper(context:Context,resId: Int,data: ArrayList<TeacherBean>) : BaseQuickAdapter<TeacherBean,BaseViewHolder>(resId,data)  {

    override fun convert(holder: BaseViewHolder, item: TeacherBean) {
        var ivUserHead = holder.getView<ImageView>(R.id.ivUserHead)
        var tvUserName = holder.getView<TextView>(R.id.tvUserName)//
        var tvRank = holder.getView<TextView>(R.id.tvRank)//
        var tvDeputy = holder.getView<TextView>(R.id.tvDeputy)//
        var tvIntro = holder.getView<TextView>(R.id.tvIntro)//
        tvUserName.text = item.teacherName
        tvRank.text = item.workgroup+item.level
        tvDeputy.text = "代表课程：毛泽东选集"
        tvIntro.text = "5个收录课程  ·  1156浏览"
        Glide.with(context).load(item.head).apply(RequestOptions().placeholder(R.mipmap.ic_launcher).circleCrop()).into(ivUserHead)
    }
}