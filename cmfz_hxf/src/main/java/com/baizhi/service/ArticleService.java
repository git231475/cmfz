package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface ArticleService {
    @Autowired
    Map<String,Object> queryAll(Integer page, Integer rows);

    String add(Article article);

    void delete(String[] id);

}
