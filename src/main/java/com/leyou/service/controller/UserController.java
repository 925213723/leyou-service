package com.leyou.service.controller;

import com.leyou.service.pojo.*;
import com.leyou.service.service.UserService;
import com.leyou.service.utils.DownExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryUserById(@PathVariable(value = "id") Long id) {
        User users = userService.queryUserById(id);
        return users;
    }

    @PostMapping("/teaSub")
    public List<Tea> queryTeaSub(@RequestBody ParamMap paramMap) {
        List<Tea> teaSub = userService.findTeaSub(paramMap);
        return teaSub;
    }

    @GetMapping("/downExcel")
    public Map<String, Object> downExcel(HttpServletResponse response) throws Exception {
        try {
            List<String> heardList = new ArrayList<>();
            List<Header> header = userService.findHeader();
            for (Header m : header) {
                String heardNm = (String) m.getHeardNm();
                heardList.add(heardNm);
            }
            List<DataScore> data = userService.findData();
            Map<String, Object> map = DownExcel.exportRecordTask(response, heardList, data);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("错误");
        }
    }
}
