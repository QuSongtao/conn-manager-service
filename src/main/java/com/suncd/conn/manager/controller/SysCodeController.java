/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.ConnConfSyscodeService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mgr/sysCode")
public class SysCodeController {
    @Autowired
    private ConnConfSyscodeService connConfSyscodeService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Response findAll(){
        return connConfSyscodeService.getAll();
    }
    @RequestMapping(value = "/{id}/{value}",method = RequestMethod.POST)
    public Response changeStatus(@PathVariable("id") String id, @PathVariable("value") String value){
        return connConfSyscodeService.changeStatus(id,value);
    }
}
