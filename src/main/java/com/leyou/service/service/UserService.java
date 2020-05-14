package com.leyou.service.service;

import com.leyou.service.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

     User queryUserById(Long id);

     List<Tea>findTeaSub(ParamMap param);

     List<Header> findHeader();

     List<DataScore> findData();
}
