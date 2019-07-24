package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.github.mustachejava.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String, Object> login(Admin admin){ Map<String, Object> map = new HashMap<>();
        Admin selectByUsername = adminDao.selectByUsername(admin.getUsername());
        if (selectByUsername == null) {
            map.put("code", "300");
            map.put("message", "用户名不存在");
        } else if (selectByUsername.getPassword().equals(admin.getPassword())) {
            map.put("code", "200");
            map.put("message", "登录成功");
        } else {
            map.put("code", "400");
            map.put("message", "密码错误");
        }
        return map;
    }
}