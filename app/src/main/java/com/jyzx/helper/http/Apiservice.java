package com.jyzx.helper.http;

import rxhttp.wrapper.annotation.DefaultDomain;

/**
 * user: Administrator
 * date:2021/8/2
 * EXP:
 */

public class Apiservice {

    public static String httpPath = "api/";
    //端口号
    public static String PORT = ":8089/";

    //测试环境地址
    @DefaultDomain
    public static String TEST_ADDRESS = "http://zsw.jy365.net/"+httpPath;


    public static String LOCAL_ADDRESS = "http://192.168.1.216"+PORT+httpPath;


    /**
     * 以下是文件路径名称
     */

    /**
     * 分页加载师资数据
     */
    public static String TEACHER_LIST = "mobile/teacherList";

    /**
     * 获取师资详情
     */
    public static String TEACHERE_DETAIL = "mobile/teacherInfo";

    /**
     * 获取课程库列表
     */
    public static String  COURSE_LIST = "mobile/course/courseList";

    /**
     * 获取图书列表
     */
    public static String BOOK_LIST = "mobile/book/bookList";

    /**
     *
     */
    public static String SEARCH_LIST = "mobile/searchList";

    /**
     * 获取第一页的标题
     */
    public static String HOME_TITLE = "mobile/getFirstPage";
    public static String  LOGIN = "mobile/ValidateUser";

}
