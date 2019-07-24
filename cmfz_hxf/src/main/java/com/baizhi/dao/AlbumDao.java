package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查询所有专辑
    List<Album> selectAll(@Param("begin") Integer begin, @Param("rows") Integer rows);
    //查询总条数
    Integer selectRecords();
    void insert(Album album);
    //删除轮播图
    void delete(@Param("ids") String[] ids);
    //查询一个
    Album selectOne(String id);
    //修改专辑图路径
    void updateCover(Album album);
}
