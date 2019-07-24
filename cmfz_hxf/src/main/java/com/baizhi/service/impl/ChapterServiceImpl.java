package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    public String add(Chapter chapter) {
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        chapterDao.insert(chapter);
        return id;
    }
    @Override
    public void delete(String[] id) {
        chapterDao.delete(id);
    }

    @Override
    public void modifyDownPath(Chapter chapter) {

        chapterDao.updateDownPath(chapter);
    }


    @Override
    public Chapter selectOne(String id) {
        Chapter chapter = chapterDao.selectOne(id);
        return chapter;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page,Integer rows,String albumId) {
        Map<String,Object> map =new HashMap<>();
        Integer records = chapterDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin = (page-1)*rows;
        List<Chapter> carousels = chapterDao.selectAll(begin, rows,albumId);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",carousels);
        return map;
    }


}
