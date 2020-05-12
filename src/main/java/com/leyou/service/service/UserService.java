package com.leyou.service.service;

import com.leyou.service.pojo.ParamMap;
import com.leyou.service.pojo.Tea;
import com.leyou.service.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

     User queryUserById(Long id);

     List<Tea>findTeaSub(ParamMap param);
}
