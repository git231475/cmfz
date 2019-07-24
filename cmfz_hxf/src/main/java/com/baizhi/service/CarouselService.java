package com.baizhi.service;

import com.baizhi.entity.Carousel;

import java.util.Map;

public interface CarouselService {

    /**
     * @添加轮播图
     * @param carousel
     */
    String add(Carousel carousel);


    void delete(String[] id);

    /**
     * @修改图片路径
     * @param carousel
     */
    void modify(Carousel carousel);

    /**
     * 修改图片状态
     * @param carousel
     */
    void modify1(Carousel carousel);
    /**
     * 查一个
     * @param id
     * @return
     */
    Carousel selectOne(String id);

    /**
     * 查询所有轮播图
     * @return
     */
    Map<String,Object> queryAll(Integer page, Integer rows);

}
