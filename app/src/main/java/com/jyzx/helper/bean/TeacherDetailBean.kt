package com.jyzx.helper.bean

/**
 * 用户名: zcm
 * date: 2021/9/10
 * des:
 **/
data class TeacherDetailBean(
    val courseList: List<Course>,
    val teacherVo: TeacherVo
)

data class Course(
    val clickCount: Int,
    val courseName: String,
    val thumbnail: String,
    val courseUrl: String,
    val creator: String
)

data class TeacherVo(
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
)