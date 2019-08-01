package com.university.haha.service.impl;

import com.university.haha.dao.BlogDao;
import com.university.haha.domain.Blog;
import com.university.haha.domain.Category;
import com.university.haha.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(value = BlogService.SERVICE)
public class BlogServiceImpl implements BlogService{
    @Resource(name = BlogDao.SERVICE)
    private BlogDao blogDao;

    /**
     * 获取博客类别集合
     * @param hql
     * @return
     */
    @Override
    public List<Category> getCategory(String hql) {
        List<Category> categories=blogDao.get(hql);
        return categories;
    }

    /**
     * 获取指定博客类别
     * @param hql
     * @param map
     * @return
     */
    @Override
    public Category getCategory(String hql, Map<String, Object> map) {
         List<Category> categories=blogDao.get(hql,map);
         if (categories!=null)
         {
             return categories.get(0);
         }
         else {
             return null;
         }
    }


    /**
     * 保存撰写好的博客
     * @param blog
     * @return
     */
    @Override
    public Integer saveBlog(Blog blog) {
        Integer status= (Integer) blogDao.save(blog);
        return status;
    }

    /**
     * 获取指定数量的博客
     * @param hql
     * @param limit
     * @return
     */
    @Override
    public List<Blog> getBlog(String hql,int limit) {
        List<Blog> blogs=blogDao.get(hql,limit);
        if (blogs!=null)
        {
            return blogs;
        }else {
            return null;
        }

    }

    /**
     * 删除指定博客
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public Integer deleteBlog(Class clazz, Serializable id) {
        return blogDao.delete(clazz,id);
    }

    /**
     * 获取指定博客
     * @param hql
     * @param map
     * @return
     */
    @Override
    public Blog getBlog(String hql, Map<String, Object> map) {
        List<Blog> blogs=blogDao.get(hql,map);
        if (blogs!=null)
        {
            return blogs.get(0);
        }
        else {
            return null;
        }
    }


    /**
     * 更新指定博客
     * @param clazz
     * @param id
     * @param blog
     * @return
     */
    @Override
    public Integer updateBlog(Class clazz, Serializable id, Blog blog) {
        Integer status=blogDao.updateBlog(clazz,id,blog);
        return status;
    }

    /**
     * 分页获取博客信息
     * @param hql
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Blog> getBlog(String hql,Integer page, Integer limit) {
        return blogDao.get(hql,page,limit);
    }

    /**
     * 获取指定数据的数量
     * @param hql
     * @return
     */
    @Override
    public long  getCount(String hql) {
        return blogDao.getCount(hql);
    }


    /**
     * 获取博客集合
     * @param hql
     * @return
     */
    @Override
    public List<Blog> getBlog(String hql) {
        return blogDao.get(hql);
    }




}
