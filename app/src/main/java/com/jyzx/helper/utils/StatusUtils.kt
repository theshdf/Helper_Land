package com.jyzx.helper.utils
import androidx.fragment.app.FragmentActivity
import com.jyzx.helper.R
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig

/**
 * 修改状态栏的工具类
 */
internal class StatusUtils {

    companion object{
        /**
         *  修改首页的状态栏
         */
        fun  updateHomeStatus(context: FragmentActivity){
            val config = BarConfig.newInstance().fitWindow(false)
                .drawableRes(R.drawable.home_top_sp)
                .light(true)
            UltimateBarX.with(context).config(config).apply {
                applyStatusBar()
                applyNavigationBar()//不设置导航栏 pad横屏右侧会显示会空出来一块
            }
        }
        fun  updateActivityStatus(context: FragmentActivity){
            val config = BarConfig.newInstance().fitWindow(false)
                .drawableRes(R.color.text_green_05c)
                .light(true)
            UltimateBarX.with(context).config(config).apply {
                applyStatusBar()
                applyNavigationBar()//不设置导航栏 pad横屏右侧会显示会空出来一块
            }
        }
        fun  updateActivityStatusByColor(context: FragmentActivity){
            val config = BarConfig.newInstance().fitWindow(false)
                .drawableRes(R.color.white)
                .light(true)
            UltimateBarX.with(context).config(config).apply {
                applyStatusBar()
                applyNavigationBar()//不设置导航栏 pad横屏右侧会显示会空出来一块
            }
        }
    }
}