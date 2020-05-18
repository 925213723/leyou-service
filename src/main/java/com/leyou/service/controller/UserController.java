package com.leyou.service.controller;

import com.leyou.service.pojo.*;
import com.leyou.service.service.UserService;
import com.leyou.service.utils.DownExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @PostMapping("/downExcel")
    public void downExcel(@RequestBody List<Map<String, String>> dataScore, HttpServletResponse response) throws Exception {
        try {
            /*List<String> heardList = new ArrayList<>();
            List<Header> header = userService.findHeader();
            for (Header m : header) {
                String heardNm = (String) m.getHeardNm();
                heardList.add(heardNm);
            }
            List<DataScore> data = userService.findData();*/
            List<String> heardList = Arrays.asList("对象", "语文", "数学", "英语");


            DownExcel.exportRecordTask(response, heardList, dataScore);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("错误");
        }
    }

    //导入excel
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String result = userService.readExcelFile(file);
        map.put("message", result);
        return map;
    }
}
