package com.jyzx.helper.http.intercept

import com.jyzx.helper.utils.JsonUtil
import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.StringBuilder

/**
 * 用户名: zcm
 * date: 2021/9/14
 * des:
 **/
class HttpLogger : HttpLoggingInterceptor.Logger {

    private  var mMessage: StringBuilder = StringBuilder()

    override fun log( message: String) {
        var msg = message
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((msg.startsWith("{") && msg.endsWith("}"))
            || (msg.startsWith("[") && msg.endsWith("]"))) {
            msg = JsonUtil.formatJson(JsonUtil.decodeUnicode(msg));
        }
        mMessage.append(msg.plus("\n"));
        // 响应结束，打印整条日志
        if (msg.startsWith("<-- END HTTP")) {
            Logger.d(mMessage.toString())
        }
    }
}