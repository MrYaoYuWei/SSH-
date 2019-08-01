package com.university.haha.service;

import com.university.haha.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    public static final String SERVICE = "com.university.haha.UserServiceImpl";

    /**
     * 获取用户信息
     * @return
     */
    public User getUser();
}
