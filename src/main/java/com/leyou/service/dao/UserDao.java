package com.leyou.service.dao;

import com.leyou.service.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> queryUserId(@Param("id") long id);
}
