package com.university.haha.dao;


import com.university.haha.domain.Blog;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonDao<T>{
    public static final String SERVICE="com.university.haha.dao.CommonDaoImpl";
    /**
     * 返回码正常值
     */
    public static final Integer OK=0;
    /**
     * 返回码非正常值
     */
    public static final Integer NOT=1;


    /**
     *获取有限数据
     * @param hql
     * @param limit
     * @return
     */
    public List<T> get(String hql,int limit);

    /**
     * 获取数据集合
     * @param hql
     * @return
     */
    public List<T> get(String hql);

    /**
     * 获取指定数据集合
     * @param hql
     * @param params
     * @return
     */
    public List<T> get(String hql, Map<String, Object> params);


    /**
     * 保存数据
     * @param o
     * @return
     */
    public Integer save(T o);


    /**
     * 获取指定数据
     * @param clazz
     * @param id
     * @return
     */
    public T get(Class clazz,Serializable id);

    /**
     * 删除数据
     * @param clazz
     * @param id
     * @return
     */
    public Integer delete(Class clazz, Serializable id);


    /**
     * 分页获取数据
     * @param hql
     * @param page
     * @param limit
     * @return
     */
    public List<T> get(String hql,int page,int limit);

    /**
     * 获取数据数量
     * @param hql
     * @return
     */
    public long getCount(String hql);


}
