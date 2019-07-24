package com.baizhi.dao;

import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao {
    List<User> selectAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    Integer selectRecords();

    void insert(Guru guru);

    void update(Guru guru);

    Guru selectOne(String id);

    void updateProfile(Guru guru);

}
