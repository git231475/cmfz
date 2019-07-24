package com.baizhi.service.impl;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("carouselService")
@Transactional
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;
    @Override
    public String add(Carousel carousel) {
        String id = UUID.randomUUID().toString();
        carousel.setId(id);
        carouselDao.insert(carousel);
        return  id;
    }
    @Override
    public void delete(String[] id) {
        carouselDao.delete(id);
    }

    @Override
    public void modify(Carousel carousel) {
        carouselDao.updateImgPath(carousel);
    }

    @Override
    public void modify1(Carousel carousel) {
        carouselDao.update(carousel);
    }


    @Override
    public Carousel selectOne(String id) {
        Carousel carousel = carouselDao.selectOne(id);
        return carousel;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAll(Integer page,Integer rows) {
        Map<String,Object> map =new HashMap<>();
        Integer records = carouselDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin = (page-1)*rows;
        List<Carousel> carousels = carouselDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",carousels);
        return map;
    }


}
