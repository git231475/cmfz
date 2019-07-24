package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @RequestMapping("add")
    public void add(Carousel carousel){
        carouselService.add(carousel);
    }
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> stringObjectMap = carouselService.queryAll(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("edit")
    public String edit(Carousel carousel,String oper,String[] id){
        if("edit".equals(oper)){
            //修改
            carouselService.modify1(carousel);

        }else if("add".equals(oper)) {
            //添加
            String s = carouselService.add(carousel);
            return  s;
        }else {
            //删除
            carouselService.delete(id);
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile imgPath, HttpServletRequest request, HttpServletResponse response){
        //文件上传
        String originalFilename = imgPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path,originalFilename));
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImgPath(originalFilename);
            carouselService.modify(carousel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库路径


    }
}
