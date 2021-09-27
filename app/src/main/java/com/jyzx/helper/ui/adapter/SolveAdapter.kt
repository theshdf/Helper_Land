package com.jyzx.helper.ui.adapter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.VideoBean
import com.tamsiree.rxkit.RxPhotoTool
import rxhttp.wrapper.OkHttpCompat.url




/**
user: Administrator
date:2021/8/5
EXP:
 */

class SolveAdapter( res: Int,  datas: ArrayList<VideoBean>,context: Context?) : BaseQuickAdapter<VideoBean,BaseViewHolder>(res,datas) {

    override fun convert(holder: BaseViewHolder, item: VideoBean) {
        holder.setText(R.id.tvTitle,item.title)
        holder.setText(R.id.tvDes,item.des)
        holder.setText(R.id.tvVideoTime,item.videoTime+":00")
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        var ivUserHead = holder.getView<ImageView>(R.id.ivUserHead)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(10))
        val option1 = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .circleCrop()
        Glide.with(context).load(item.videoCover).apply(option).into(ivCover)
        Glide.with(context).load( item.authorHead).apply(option1).into(ivUserHead)
    }
}