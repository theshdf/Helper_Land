package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.Course
import com.jyzx.helper.bean.VideoBean

/**
 * 专家相关的适配器
 */
class ExperRelaAdapter(resId: Int, data: ArrayList<Course>, context: Context?) : BaseQuickAdapter<Course,BaseViewHolder>(resId,data){

    override fun convert(holder: BaseViewHolder, item: Course) {
        holder.setText(R.id.tvTitle,item.name)
        holder.setText(R.id.tvDes,"点击量"+item.clickCount)
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
        Glide.with(context).load(item.thumbnail).error(R.mipmap.ic_launcher).apply(option).into(ivCover)
    }
}