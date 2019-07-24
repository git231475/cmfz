package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    List<Article> selectAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    Integer selectRecords();

    void insert(Article article);

    void delete(String[] id);
}
