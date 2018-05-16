package com.zj.rcbt.spring.controller;

import java.io.Serializable;
import java.util.HashMap;

public class RequestResult extends HashMap implements Serializable {
    public RequestResult() {
    }

    public RequestResult(int code) {
        this.setStatus(code);
    }

    public static RequestResult sucessRes() {
        RequestResult res = new RequestResult(0);
        return res;
    }

    public static RequestResult errorRes(int code) {
        RequestResult res = new RequestResult(code);
        return res;
    }

    public static RequestResult statusCode(int code) {
        RequestResult res = new RequestResult(code);
        return res;
    }

    public void setStatus(int code) {
        this.put("status", code);
    }

    public String getErrorMsg() {
        return (String) this.get("error");
    }

    public void setErrorMsg(String errorMsg) {
        this.put("error", errorMsg);
    }

    public Object getData() {
        return this.get("data");
    }

    public void setData(Object data) {
        this.put("data", data);
    }
}
