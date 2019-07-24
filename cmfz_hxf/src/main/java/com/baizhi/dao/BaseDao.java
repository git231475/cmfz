package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    List<T> selectAll(@Param("begin") Integer begin,@Param("rows") Integer rows);
    Integer selectRecords();
    //添加轮播图
    void insert(T t);
}
