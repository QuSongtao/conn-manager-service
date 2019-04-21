/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.entity.ConnConfUser;
import com.suncd.conn.manager.service.ConnConfUserService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mgr/user")
public class UserController {
    @Autowired
    private ConnConfUserService connConfUserService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Response findAll(int pageIndex, int pageSize){
        return connConfUserService.getAll(pageIndex,pageSize);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response addUser(ConnConfUser connConfUser){
        return connConfUserService.addUser(connConfUser);
    }
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public Response deleteUser(String ids,String loginNames){
        return connConfUserService.deleteUser(ids.split(","),loginNames.split(","));
    }
    @RequestMapping(value = "",method = RequestMethod.PATCH)
    public Response updateUser(ConnConfUser connConfUser){
        return connConfUserService.updateUser(connConfUser);
    }
}
