package com.university.haha.util.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.haha.util.CommonUtil;
import org.springframework.stereotype.Component;


/**
 * json转换工具
 * @param <T>
 */
@Component(value = CommonUtil.SERVICE)
public class CommonUtilImpl<T>   implements CommonUtil<T>{
    ObjectMapper mapper = new ObjectMapper();


    /**
     * 将对象转换为json数据
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String getJson(T o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
