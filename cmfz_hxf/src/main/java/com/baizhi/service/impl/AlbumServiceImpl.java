package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Override
    public String add(Album album) {
        String id = UUID.randomUUID().toString();
        album.setId(id);
        albumDao.insert(album);
        return  id;
    }
    @Override
    public void delete(String[] id) {
        albumDao.delete(id);
    }

    @Override
    public void modify(Album album) {

        albumDao.updateCover(album);
    }


    @Override
    public Album selectOne(String id) {
        Album album = albumDao.selectOne(id);
        return album;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page,Integer rows) {
        Map<String,Object> map =new HashMap<>();
        Integer records = albumDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin = (page-1)*rows;
        List<Album> carousels = albumDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",carousels);
        return map;
    }


}
