package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {

    List<Chapter> selectAll(@Param("begin") Integer begin, @Param("rows") Integer rows ,@Param("albumId") String albumId);

    Integer selectRecords();

    void insert(Chapter chapter);

    void delete(@Param("ids") String[] ids);

    Chapter selectOne(String id);

    void updateDownPath(Chapter chapter);
}
