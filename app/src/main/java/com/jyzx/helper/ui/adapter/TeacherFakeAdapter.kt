package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.VideoBean

/**
 * 教师相关的适配器
 */
class TeacherFakeAdapter(resId: Int, data: ArrayList<VideoBean>, context: Context?) : BaseQuickAdapter<VideoBean,BaseViewHolder>(resId,data){

    override fun convert(holder: BaseViewHolder, item: VideoBean) {

        holder.setText(R.id.tvTitle,item.author)
        holder.setText(R.id.tvDes,item.des)
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(6))
        Glide.with(context).load(item.authorHead).apply(option).into(ivCover)
    }
}