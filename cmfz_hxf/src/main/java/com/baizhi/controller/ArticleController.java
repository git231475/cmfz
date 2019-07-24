package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> stringObjectMap = articleService.queryAll(page,rows);
        return stringObjectMap;
    };
    @RequestMapping("edit")
    public String edit(Article article, String oper, String[] id){
        if("edit".equals(oper)){
            //修改

        }else if("add".equals(oper)) {
            //添加
            String s = articleService.add(article);
            return  s;
        }else {
            //删除
            articleService.delete(id);
        }
        return null;
    }
    @RequestMapping("upload")
    public Map<String , Object> upload(MultipartFile file, HttpServletRequest request){
        String originalFilename = file.getOriginalFilename();
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file1 = new File(articlePic);
        if(!file1.exists()){
            file1.mkdir();
        }
        Map<String , Object> map = new HashMap<>();
        try {
            file.transferTo(new File(articlePic,originalFilename));
            map.put("error",0);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+originalFilename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+originalFilename);
            return map;
        }
    }
    @RequestMapping("showAll")
    public Map<String , Object> showAll(HttpServletRequest request){
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file = new File(articlePic);
        String[] list = file.list();
        Map<String , Object> map = new HashMap<>();
        map.put("current_url","http://localhost:8888/cmfz/articlePic/");
        map.put("total_count",list.length);
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
            Map<String , Object> map1 = new HashMap<>();
            map1.put("is_dir",false);
            map1.put("has_file",false);
            File file1 = new File(articlePic,s);
            long length = file1.length();
            map1.put("filesize",length);
            map1.put("is_photo",true);
            String substring = s.substring(s.indexOf(".") + 1);
            map1.put("filetype",substring);
            map1.put("filename",s);
            map1.put("datetime",new Date());
            lists.add(map1);
        }
        map.put("file_list",lists);
        return map;
    }
}
