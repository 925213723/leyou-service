package com.leyou.service.service.impl;

import com.leyou.service.dao.UserDao;
import com.leyou.service.pojo.*;
import com.leyou.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Header> findHeader() {
        List<Header> header = userDao.findHeader();
        return header;
    }

    @Override
    public List<DataScore> findData() {
        List<DataScore> data = userDao.findData();
        return data;
    }
}
