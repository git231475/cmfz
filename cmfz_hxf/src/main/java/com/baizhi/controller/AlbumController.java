package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AlbumService;
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
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        Map<String, Object> stringObjectMap = albumService.queryAll(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("edit")
    public String edit(Album album, String oper, String[] id){
        if("edit".equals(oper)){
            //修改

        }else if("add".equals(oper)) {
            //添加
            String s = albumService.add(album);
            return  s;
        }else {
            //删除
            albumService.delete(id);
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile cover, HttpServletRequest request, HttpServletResponse response){
        //文件上传
        String originalFilename = cover.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("albumPic");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            cover.transferTo(new File(path,originalFilename));
            Album album = new Album();
            album.setId(id);
            album.setCover(originalFilename);
            albumService.modify(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库路径


    }
}
