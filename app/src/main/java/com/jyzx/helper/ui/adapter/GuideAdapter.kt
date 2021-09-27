package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.VideoBean

/**
user: Administrator
date:2021/8/5
EXP:专家引导页
 */

class GuideAdapter(val res:Int,val datas: ArrayList<VideoBean>,context: Context?) : BaseQuickAdapter<VideoBean,BaseViewHolder>(res,datas) {
    override fun convert(holder: BaseViewHolder, item: VideoBean) {
        holder.setText(R.id.tvTitle,item.title)
        holder.setText(R.id.tvDes,item.author)
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(10))
        Glide.with(context).load(item.leadCover).apply(option).into(ivCover)
    }
}