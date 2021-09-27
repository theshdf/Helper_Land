package com.jyzx.helper.bean

/**
 * 用户名: zcm
 * date: 2021/9/10
 * des:
 **/
data class ResponseLoginBean<T> (val Type: Int,
                                 val Message: String,
                                 val Data: T,
                                 val count:Int,
                                 val IsSuccess: Boolean
                         )