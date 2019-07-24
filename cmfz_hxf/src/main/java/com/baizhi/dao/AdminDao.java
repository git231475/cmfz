package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
    //管理员登录
    Admin selectByUsername(String username);

}
