package com.jyzx.helper.ui.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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

class VideoRelaAdapter(val res:Int, val datas: ArrayList<VideoBean>, context: Context) : BaseQuickAdapter<VideoBean,BaseViewHolder>(res,datas) {
    override fun convert(holder: BaseViewHolder, item: VideoBean) {

        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        var ivPlay = holder.getView<ImageView>(R.id.ivPlay)
        var tvPlay = holder.getView<TextView>(R.id.tvPlay)
        var ivPlayLogo = holder.getView<ImageView>(R.id.ivPlayLogo)
        holder.setText(R.id.tvOrder,(holder.layoutPosition+1).toString())
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(10))
        Glide.with(context).load(item.videoCover).apply(option).into(ivCover)
        if(item.isPlay){
            ivPlay.visibility = View.GONE
            tvPlay.visibility = View.VISIBLE
            ivPlayLogo.visibility = View.VISIBLE
        }
        else{
            tvPlay.visibility = View.GONE
            ivPlay.visibility = View.VISIBLE
            ivPlayLogo.visibility = View.GONE
        }
    }
}