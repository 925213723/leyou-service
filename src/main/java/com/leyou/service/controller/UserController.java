package com.leyou.service.controller;

import com.leyou.service.pojo.User;
import com.leyou.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public List<User> queryUserById(@PathVariable(value = "id") Long id){
        List<User> users = userService.queryUserById(id);
        return  users;
    }
}
