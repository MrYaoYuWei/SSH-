package com.university.haha.dao;

import com.university.haha.domain.Blog;

import java.io.Serializable;

public interface BlogDao extends  CommonDao {
    public static final String SERVICE="com.university,haha,dao.impl.BlogDaoImpl";

    /**
     * 更新指定博客信息
     * @param blogClass
     * @param id
     * @param blog
     * @return
     */
    public Integer updateBlog(Class<Blog> blogClass, Serializable id, Blog blog);
}
