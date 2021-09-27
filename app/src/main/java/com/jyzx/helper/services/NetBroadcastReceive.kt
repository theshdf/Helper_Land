package com.jyzx.helper.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.baidu.idl.main.facesdk.statistic.NetWorkUtil
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.NetworkUtils
import com.tamsiree.rxkit.RxTool
import com.tamsiree.rxkit.view.RxToast

/**
 * 用户名: zcm
 * date: 2021/9/17
 * des: 检测网络状态的广播
 **/
class NetBroadcastReceive : BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //如果是网络
            var connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var netWorkInfo = connectManager.activeNetworkInfo
            if(netWorkInfo!=null&& netWorkInfo.isAvailable){
                //如果有网络
                RxToast.showToast("网络恢复正常")
            }
            else{
                RxToast.showToast("网络已经断开")
            }
        }
    }
}