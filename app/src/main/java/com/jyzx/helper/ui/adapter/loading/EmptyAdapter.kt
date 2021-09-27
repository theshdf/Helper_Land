package com.jyzx.helper.ui.adapter.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.R

/**
 * 用户名: zcm
 * date: 2021/8/31
 * des:
 **/
class EmptyAdapter: LoadingHelper.Adapter<LoadingHelper.ViewHolder>() {

    private val height = ViewGroup.LayoutParams.MATCH_PARENT

    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {
        val layoutParams = holder.rootView.layoutParams
        layoutParams.height = height
        holder.rootView.layoutParams = layoutParams
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LoadingHelper.ViewHolder {
        return  LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_loading, parent, false));
    }
}