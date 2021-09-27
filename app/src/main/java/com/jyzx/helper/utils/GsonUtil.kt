package com.jyzx.helper.utils

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.Gson
import com.jyzx.helper.bean.UserInfo

/**
 * 用户名: zcm
 * date: 2021/9/27
 * des:
 **/
class GsonUtil {
    companion object{

        /**
         * 将json转换为对象
         */
        fun getObjFromJson(json: String): UserInfo{
            var gson = Gson()
            return gson.fromJson(json, UserInfo::class.java)
        }

        /**
         * 将对象转换为json
         */
        fun getJsonFromObj(obj: Any): String{
            var gson = Gson()
           return gson.toJson(obj)
        }
    }
}