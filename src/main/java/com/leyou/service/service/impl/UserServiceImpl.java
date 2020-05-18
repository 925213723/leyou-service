package com.leyou.service.service.impl;

import com.leyou.service.dao.UserDao;
import com.leyou.service.pojo.*;
import com.leyou.service.service.UserService;
import com.leyou.service.utils.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(Long id) {

        User users = userDao.queryUserId(id);
        return users;
    }

    @Override
    public List<Tea> findTeaSub(ParamMap paramMap) {
        int pageStart = (paramMap.getPageNo() - 1) * paramMap.getPageSize();
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


    @Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for (Map<String, Object> user : userList) {
            int ret = userDao.insertUser(user.get("name").toString(), user.get("sex").toString(), Integer.parseInt(user.get("age").toString()));
            if (ret == 0) {
                result = "插入数据库失败";
            }
        }
        if (userList != null && !userList.isEmpty()) {
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
}
