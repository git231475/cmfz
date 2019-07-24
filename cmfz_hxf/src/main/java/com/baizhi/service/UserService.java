package com.baizhi.service;


import com.baizhi.entity.User;

import java.util.Map;

public interface UserService {
    Map<String,Object> queryAll(Integer page,Integer rows);
    String add(User user);
    void modify(User user);
    User selectOne(String id);
    void modifyProfile(User user);

}
