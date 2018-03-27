package com.juphoon.app.common;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

/**
 * Created by ztf on 2018/2/5
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
public class ServerResponse implements Serializable {

    private boolean ret = true;
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServerResponse(int code, Object data, String error) {
        this.code = code;
        this.data = data;
        this.msg = error;
        if (code!=1)
        {
            ret=false;
        }
    }

    private static int SUCCESS_CODE = 1;
    private static int ERROR_CODE = 0;
    private static int NOT_LOGIN_CODE = -1;
    private static int NO_AUTHORIZATION = -2;

    public static ServerResponse success() {
        return new ServerResponse(SUCCESS_CODE, null, null);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(SUCCESS_CODE, data, null);
    }

    public static ServerResponse error() {
        return new ServerResponse(ERROR_CODE, null, null);
    }

    public static ServerResponse error(String message) {
        return new ServerResponse(ERROR_CODE, null, message);
    }

    public static ServerResponse notLogin() {
        return new ServerResponse(NOT_LOGIN_CODE, null, null);
    }

    public static ServerResponse notAuthorization() {
        return new ServerResponse(NO_AUTHORIZATION, null, null);
    }

}
