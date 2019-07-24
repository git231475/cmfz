package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer records = articleDao.selectRecords();
        Integer total =records%rows==0?records/rows:records/rows+1;
        Integer begin =(page-1)*rows;
        List<Article> articles = articleDao.selectAll(begin,rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",articles);
        return map;
    }

    @Override
    public String add(Article article) {
        String id = UUID.randomUUID().toString();
        article.setId(id);
        articleDao.insert(article);
        return id;
    }

    @Override
    public void delete(String[] id) {
        articleDao.delete(id);
    }
}
