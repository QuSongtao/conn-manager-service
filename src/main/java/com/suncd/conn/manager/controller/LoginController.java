package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mgr/login")
public class LoginController {
    @Value("${system.username}")
    private String user;
    @Value("${system.password}")
    private String pwd;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response login(String username,String password){
        if(user.equals(username) && pwd.equals(password)){
            return new Response<>().success("登录成功!");
        }else {
            return new Response<>().failure("账号或口令错误!");
        }
    }
}
