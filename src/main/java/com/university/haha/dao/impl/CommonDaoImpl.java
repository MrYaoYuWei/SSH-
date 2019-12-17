package com.university.haha.dao.impl;

import com.university.haha.dao.CommonDao;


import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

@Repository(value = CommonDao.SERVICE)
public class CommonDaoImpl<T> implements CommonDao<T> {
    @Resource(name = "localSessionFactoryBean")
    private SessionFactory sessionFactory;
    private List<T> list;

    /**
     * 获取有限数据
     * @param hql
     * @param limit
     * @return
     */
    @Override
    public List<T> get(String hql, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setMaxResults(limit);
        list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 获取数据集合
     * @param hql
     * @return
     */
    @Override
    public List<T> get(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }


    /**
     * 获取指定数据集合
     * @param hql
     * @param params
     * @return
     */
    @Override
    public List<T> get(String hql, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }


    /**
     * 获取指定数据
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public T get(Class clazz, Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(clazz, id);
    }


    /*
     保存数据
     */
    @Override
    public Integer save(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return CommonDao.OK;
    }


    /**
     * 删除指定数据
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public Integer delete(Class clazz, Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        T o = this.get(clazz, id);
        session.delete(o);
        return CommonDao.OK;
    }


    /**
     * 分页获取数据
     * @param hql
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<T> get(String hql, int page, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 获取数据数量
     * @param hql
     * @return
     */
    @Override
    public long getCount(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.uniqueResult() == null ? 0 : (long) query.uniqueResult();
    }


}
