package com.university.haha.service;

import com.university.haha.domain.Blog;
import com.university.haha.domain.Category;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BlogService {

    public static final String SERVICE="com.university.haha.service.impl.BlogServiceImpl";


    /**
     * 获取博客类别集合
     * @param hql
     * @return
     */
    public List<Category> getCategory(String hql);


    /**
     * 根据条件获取博客类别
     * @param hql
     * @param map
     * @return
     */
    public Category getCategory(String hql,Map<String,Object> map);


    /**
     * 保存撰写的博客
     * @param blog
     * @return
     */
    public Integer saveBlog(Blog blog);


    /**
     * 获取限定的博客数量
     * @param hql
     * @param limit
     * @return
     */
    public  List<Blog> getBlog(String hql, int limit);


    /**
     * 删除指定的博客
     * @param clazz
     * @param id
     * @return
     */
    public Integer deleteBlog(Class clazz,Serializable id);

    /**
     * 获取指定博客
     * @param hql
     * @param map
     * @return
     */
    public Blog getBlog(String hql,Map<String,Object> map);


    /**
     * 更新指定博客信息
     * @param clazz
     * @param id
     * @param blog
     * @return
     */
    public Integer updateBlog(Class clazz,Serializable id,Blog blog);


    /**
     * 分页获取博客信息
     * @param hql
     * @param page
     * @param limit
     * @return
     */
    public  List<Blog> getBlog(String hql,Integer page,Integer limit);

    /**
     * 获取指定数据的数量
     * @param hql
     * @return
     */
    public  long  getCount(String hql);


    /**
     * 获取博客集合
     * @param hql
     * @return
     */
    public  List<Blog> getBlog(String hql);


}
