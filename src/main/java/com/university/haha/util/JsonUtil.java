package com.university.haha.util;

/**
 * 普通json格式
 */
public class JsonUtil {
    private Integer status;//状态码
    private String msg;//信息

    public JsonUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonUtil() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
