package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {

    String add(Chapter chapter);


    void delete(String[] id);

    void modifyDownPath(Chapter chapter);

    /**
     * 查一个
     * @param id
     * @return
     */
    Chapter selectOne(String id);

    Map<String,Object> queryAll(Integer page, Integer rows,String albumId);

}
