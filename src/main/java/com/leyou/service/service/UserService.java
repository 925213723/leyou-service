package com.leyou.service.service;

import com.leyou.service.dao.UserDao;
import com.leyou.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

     List<User> queryUserById(Long id);
}
