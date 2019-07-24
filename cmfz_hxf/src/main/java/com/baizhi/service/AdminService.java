package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.Map;

public interface AdminService {
    //登录
    Map<String,Object> login(Admin admin);
}
