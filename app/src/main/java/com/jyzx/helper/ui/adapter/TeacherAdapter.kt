package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.TeacherBean
import com.jyzx.helper.bean.VideoBean

/**
 * 教师相关的适配器
 */
class TeacherAdapter(resId: Int, data: ArrayList<TeacherBean>,context: Context?) : BaseQuickAdapter<TeacherBean,BaseViewHolder>(resId,data){

    override fun convert(holder: BaseViewHolder, item: TeacherBean) {
        holder.setText(R.id.tvTitle,item.teacherName)
        holder.setText(R.id.tvDes,item.workgroup)
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(6))
        Glide.with(context).load(item.head).apply(option).into(ivCover)
    }
}