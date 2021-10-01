package com.jyzx.helper.bean

import com.jyzx.helper.R

data class BookBean(val bookName: String,
                    val author: String,
                    val img: String,
                    var bookPic: Int = R.mipmap.ic_launcher
                    )