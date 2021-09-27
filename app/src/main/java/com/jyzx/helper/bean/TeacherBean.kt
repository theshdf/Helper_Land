package com.jyzx.helper.bean

import java.io.Serializable

/**
 * 用户名: zcm
 * date: 2021/9/10
 * des:
 **/
data class TeacherBean(
    val clickCount: Int,
    val courseCount: Int,
    val educationExp: String,
    val head: String,
    val id: Int,
    val level: String,
    val name: String,
    val sex: String,
    val teacherName: String,
    val teachingExp: String,
    val thumbnail: String,
    val workExp: String,
    val workgroup: String
): Serializable