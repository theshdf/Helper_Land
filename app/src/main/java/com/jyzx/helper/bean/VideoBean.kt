package com.jyzx.helper.bean

import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.utils.FakeDataUtil
import java.io.Serializable

/**
user: Administrator
date:2021/8/5
EXP:
 */

data class VideoBean (
    val date: String,
    val commentCount: Int,
    val likeCount: Int,
    val videoUrl: String,
    val author: String,
    val title: String,
    val des: String,
    val authorHead: Int,
    val videoCover: Int,
    var leadCover: Int = R.mipmap.zxh_cover,
    var isPlay: Boolean = false,
    var videoTime: String = "10",
    var videoType: String = Constants.HOME_TITLE[0]
): Serializable
