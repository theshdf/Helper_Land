package com.jyzx.helper.bean

import java.io.Serializable

/**
 * 用户名: zcm
 * date: 2021/9/14
 * des:
 **/
data class BookListBean(val totalCount: Int,val bookList: ArrayList<BookBean>) : Serializable