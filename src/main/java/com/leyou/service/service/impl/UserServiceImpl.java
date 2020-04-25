package com.leyou.service.service.impl;

import com.leyou.service.dao.UserDao;
import com.leyou.service.pojo.User;
import com.leyou.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUserById(Long id) {

        List<User> users = userDao.queryUserId(id);
        return users;
    }
}
