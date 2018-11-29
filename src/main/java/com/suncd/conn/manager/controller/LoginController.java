package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
            Map<String ,String > ret = new HashMap<>();
            ret.put("message","登录成功!");
            ret.put("token","="+username+"67gsteadmintokadf1234hjk123"+password+"de12=");
            return new Response<>().success(ret);
        }else {
            return new Response<>().failure("账号或口令错误!");
        }
    }
}
