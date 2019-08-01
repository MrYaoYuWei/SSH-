package com.university.haha.dao.impl;

import com.university.haha.dao.BlogDao;
import com.university.haha.domain.Blog;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository(value = BlogDao.SERVICE)
public class BlogDaoImpl extends CommonDaoImpl implements BlogDao{

    /**
     * 更新指定博客信息
     * @param blogClass
     * @param id
     * @param blog
     * @return
     */
    @Override
    public Integer updateBlog(Class<Blog> blogClass, Serializable id, Blog blog) {
        Blog b= (Blog) this.get(blogClass,id);
        b.setTittle(blog.getTittle());
        b.setSummary(blog.getSummary());
        b.setContent(blog.getContent());
        b.getCategory().setCategoryName(blog.getCategory().getCategoryName());
        return OK;
    }
}
