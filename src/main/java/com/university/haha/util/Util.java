package com.university.haha.util;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页json格式
 * @param <T>
 */
public class Util<T> {
    private Integer code;//状态码
    private String msg;//信息
    private long count;//数据量
    private List<T> data=new ArrayList<>();//数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Util(Integer code, String msg, long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Util() {
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
