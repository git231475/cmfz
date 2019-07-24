package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer records = userDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin = (page-1)*rows;
        List<User> users = userDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",users);
        return map;
    }

    @Override
    public String add(User user) {
        String id = UUID.randomUUID().toString();
        String salt = MD5Utils.getSalt();
        user.setId(id);
        user.setSalt(salt);
        String password = MD5Utils.getPassword(user.getPassword() + salt);
        user.setPassword(password);
        userDao.insert(user);
        return id;
    }

    @Override
    public void modify(User user) {
        userDao.update(user);
    }

    @Override
    public User selectOne(String id) {
        User user = userDao.selectOne(id);
        return user;
    }

    @Override
    public void modifyProfile(User user) {
        userDao.updateProfile(user);
    }
}
