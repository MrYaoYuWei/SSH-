package com.university.haha.service.impl;

import com.university.haha.dao.UserDao;
import com.university.haha.domain.User;
import com.university.haha.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = UserService.SERVICE)
public class UserServiceImpl implements UserService {
    @Resource(name = UserDao.SERVICE)
    private UserDao adminDao;
    /**
     * 验证用户权限防止非法登录但是会抛出异常
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = null;
        try {
            username = new String(username.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        List<GrantedAuthority> authList = new ArrayList<>();
        map.put("name", username);
        List<User> users = adminDao.get("from User where userName=:name", map);
        if (users!=null)
        {
            password = users.get(0).getPassword();
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            password = passwordEncoder.encode(password);
            org.springframework.security.core.userdetails.User user=new org.springframework.security.core.userdetails.User(username,password,authList);
            return user;
        }
        else {
           return null;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public User getUser() {
        List<User> users=adminDao.get("select new User(nickName,sign) from User user where user.id=1");
        if (users!=null)
        {
            return users.get(0);
        }else {
            return null;
        }
    }
}
