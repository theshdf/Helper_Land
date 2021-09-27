package com.jyzx.helper.http;

import java.io.Serializable;

/**
 * user: Administrator
 * date:2021/8/2
 * EXP: 后台返回给前端的实体bean
 */

public class Response<T> implements Serializable {
    private int type;
    private String Message;
    private T Data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
