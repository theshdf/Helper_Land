package com.jyzx.helper.bean

/**
 * 用户名: zcm
 * date: 2021/9/10
 * des:
 **/
data class ResponseBean<T> (val code: Int,
                         val msg: String,
                         val data: T,
                         val count:Int
                         )