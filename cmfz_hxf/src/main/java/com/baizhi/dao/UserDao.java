package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> selectAll(@Param("begin") Integer begin,@Param("rows") Integer rows);
    List<User> selectAllUser();
    Integer selectRecords();

    void insert(User user);

    void update(User user);

    User selectOne(String id);

    void updateProfile(User user);

}
