/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.dataservice.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mgr")
public class CountController {

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Map count(){
        return countService.count();
    }

}
