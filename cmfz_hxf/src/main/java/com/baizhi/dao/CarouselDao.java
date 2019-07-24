package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDao extends BaseDao<Carousel> {

    void insert(Carousel carousel);
    //删除轮播图
    void delete(@Param("ids") String[] ids);
    //修改轮播图
    void update(Carousel carousel);
    //修改轮播图路径
    void updateImgPath(Carousel carousel);
    //查询一个
    Carousel selectOne(String id);
    //查询所有
    List<Carousel> selectAll();
}
