/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.entity.ConnConfDict;
import com.suncd.conn.manager.service.ConnConfDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mgr/dict")
public class DictController {
    @Autowired
    private ConnConfDictService connConfDictService;

    @RequestMapping(value = "/{typeCode}",method = RequestMethod.GET)
    public List<ConnConfDict> getCode(@PathVariable("typeCode") String typeCode){
        return connConfDictService.getByCode(typeCode);
    }
}
