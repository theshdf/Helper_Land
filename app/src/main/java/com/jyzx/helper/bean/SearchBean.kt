package com.jyzx.helper.bean

/**
 * 用户名: zcm
 * date: 2021/9/15
 * des:
 **/
data class SearchBean(val teacherTotalCount: Int,
                      val courseTotalCount: Int,
                      val bookTotalCount: Int,
                      val teacherList: ArrayList<TeacherBean>,
                      val courseList: ArrayList<Course>,
                      val bookList: ArrayList<BookBean>,
                      )
