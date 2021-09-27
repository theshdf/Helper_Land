package com.jyzx.helper.bean;


/**
 * 创建日期: 2018/6/20 0020 on 20:13.
 * 描述:
 * 作者： zxy
 */
public class UserBean {
    private  int  Type;
    private  String Message;

    public UserInfo getData() {
        return Data;
    }

    public void setData(UserInfo data) {
        Data = data;
    }

    private UserInfo Data;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
