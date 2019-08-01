package com.university.haha.dao.impl;

import com.university.haha.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository(value = UserDao.SERVICE)
public class UserDaoImpl extends CommonDaoImpl implements UserDao {
}
