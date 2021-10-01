package com.jyzx.helper.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.BookBean
import com.jyzx.helper.bean.VideoBean

/**
 * 课程相关的适配器
 */
class BookAdapter(resId: Int, data: ArrayList<BookBean>, context: Context?) : BaseQuickAdapter<BookBean,BaseViewHolder>(resId,data){

    override fun convert(holder: BaseViewHolder, item: BookBean) {
        holder.setText(R.id.tvTitle,item.bookName)
        holder.setText(R.id.tvDes,item.author+"  著")
        var ivCover = holder.getView<ImageView>(R.id.ivCover)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transform(RoundedCorners(6))
        if(item.bookPic != R.mipmap.ic_launcher){
            Glide.with(context).load(item.bookPic).apply(option).into(ivCover)
        }
        else{
            if(TextUtils.isEmpty(item.img)){
                Glide.with(context).load(item.img).apply(option).into(ivCover)
            }
        }

    }
}