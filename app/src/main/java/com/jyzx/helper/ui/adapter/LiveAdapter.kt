package com.jyzx.helper.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.VideoBean
import com.tamsiree.rxkit.RxPhotoTool

/**
user: Administrator
date:2021/8/5
EXP:
 */

class LiveAdapter(res: Int, datas: ArrayList<VideoBean>, context: Fragment) : BaseQuickAdapter<VideoBean,BaseViewHolder>(res,datas) {

    override fun convert(holder: BaseViewHolder, item: VideoBean) {
        holder.setText(R.id.tvTitle,item.title)
        holder.setText(R.id.tvUserName,item.author)
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        var ivUserHead = holder.getView<ImageView>(R.id.ivUserhead)
        var liveState = holder.getView<RelativeLayout>(R.id.rlOver)
        var tvLive = holder.getView<TextView>(R.id.tvLive)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(16))
        val option1 = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .circleCrop()
        if(holder.adapterPosition == itemCount-1){
            liveState.setBackgroundResource(R.mipmap.live_start)
            tvLive.text = "直播中"
        }
        Glide.with(context).load(item.authorHead).apply(option).into(ivCover)
        Glide.with(context).load(item.authorHead).apply(option1).into(ivUserHead)
    }
}