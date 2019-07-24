package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
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
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        Map<String, Object> stringObjectMap = userService.queryAll(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("edit")
    public String edit(User user, String oper, String[] id){
        if("edit".equals(oper)){
            //修改
            userService.modify(user);

        }else if("add".equals(oper)) {
            //添加
            String s = userService.add(user);
            return  s;
        }else {
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile profile, HttpServletRequest request, HttpServletResponse response){
        //文件上传
        String originalFilename = profile.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("userPic");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            profile.transferTo(new File(path,originalFilename));
            User user = new User();
            user.setId(id);
            user.setProfile(originalFilename);
            userService.modifyProfile(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库路径


    }
}
