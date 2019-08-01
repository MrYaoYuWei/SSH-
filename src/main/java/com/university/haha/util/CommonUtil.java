package com.university.haha.util;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * josn转换工具
 * @param <T>
 */
public interface CommonUtil<T> {
    public static final String SERVICE="com.university.haha.util.impl.CommonUtilImpl";
    public String getJson(T o) throws JsonProcessingException;
}
