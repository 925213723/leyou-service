package com.leyou.service.dao;

import com.leyou.service.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    User queryUserId(@Param("id") long id);

    List<Tea> findTeaSub(ParamMap paramMap);

   List<Header> findHeader();

   List<DataScore> findData();
}
