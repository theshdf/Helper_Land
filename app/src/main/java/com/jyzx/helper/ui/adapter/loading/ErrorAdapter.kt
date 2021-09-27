package com.jyzx.helper.ui.adapter.loading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.R

/**
 * 用户名: zcm
 * date: 2021/8/31
 * des:
 **/
class ErrorAdapter: LoadingHelper.Adapter<ErrorAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder) {
        holder.btnReload.setOnClickListener(View.OnClickListener { v: View? ->
            if (holder.onReloadListener != null) {
                holder.onReloadListener!!.onReload()
            }
        })
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_error, parent, false));
    }

 class ViewHolder internal constructor(rootView: View) : LoadingHelper.ViewHolder(rootView) {
    var btnReload: View = rootView.findViewById(R.id.btn_reload)

}
}
