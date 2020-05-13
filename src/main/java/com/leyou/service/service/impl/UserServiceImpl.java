package com.leyou.service.service.impl;

import com.leyou.service.dao.UserDao;
import com.leyou.service.pojo.ParamMap;
import com.leyou.service.pojo.Sub;
import com.leyou.service.pojo.Tea;
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
    public  User queryUserById(Long id) {

        User users = userDao.queryUserId(id);
        return users;
    }

    @Override
    public List<Tea> findTeaSub(ParamMap paramMap) {
        int pageStart = (paramMap.getPageNo()-1)* paramMap.getPageSize();
        paramMap.setPageStart(pageStart);
        List<Tea> teaSub = userDao.findTeaSub(paramMap);
        return teaSub;
    }
}
