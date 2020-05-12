package com.leyou.service.controller;

import com.leyou.service.pojo.ParamMap;
import com.leyou.service.pojo.Tea;
import com.leyou.service.pojo.User;
import com.leyou.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryUserById(@PathVariable(value = "id") Long id){
        User users = userService.queryUserById(id);
        return  users;
    }
    @PostMapping("/teaSub")
    public List<Tea> queryTeaSub(@RequestBody ParamMap paramMap){
        List<Tea> teaSub = userService.findTeaSub(paramMap);
        return  teaSub;
    }
}
