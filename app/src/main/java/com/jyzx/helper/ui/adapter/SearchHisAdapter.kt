package com.jyzx.helper.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R

/**
 * 搜索历史适配器
 */
class SearchHisAdapter(resId: Int,datas: ArrayList<String>) : BaseQuickAdapter<String,BaseViewHolder>(resId,datas) {
    override fun convert(holder: BaseViewHolder, item: String) {
        var  content = holder.getView<TextView>(R.id.tvContent)
        content.text = item
    }
}