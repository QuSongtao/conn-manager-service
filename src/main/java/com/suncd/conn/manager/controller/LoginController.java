package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.ConnConfUserService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mgr/login")
public class LoginController {
    @Autowired
    private ConnConfUserService connConfUserService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response login(String username,String password){
        return connConfUserService.login(username, password);
    }
}
