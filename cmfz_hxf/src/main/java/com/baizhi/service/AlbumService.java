package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;

import java.util.Map;

public interface AlbumService {

    String add(Album album);


    void delete(String[] id);

    /**
     * @修改图片路径
     * @param album
     */
    void modify(Album album);

    /**
     * 查一个
     * @param id
     * @return
     */
    Album selectOne(String id);

    /**
     * 查询所有专辑
     * @return
     */
    Map<String,Object> queryAll(Integer page, Integer rows);

}
