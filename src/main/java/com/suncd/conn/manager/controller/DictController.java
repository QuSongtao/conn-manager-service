/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.entity.ConnConfDict;
import com.suncd.conn.manager.service.ConnConfDictService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mgr/dict")
public class DictController {
    @Autowired
    private ConnConfDictService connConfDictService;

    @RequestMapping(value = "/{typeCode}",method = RequestMethod.GET)
    public List<ConnConfDict> getCode(@PathVariable("typeCode") String typeCode){
        return connConfDictService.getByCode(typeCode);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response add(ConnConfDict connConfDict){
        connConfDict.setId(UUID.randomUUID().toString());
        return connConfDictService.saveDict(connConfDict);
    }

    @RequestMapping(value = "",method = RequestMethod.PATCH)
    public Response update(ConnConfDict connConfDict){
        return connConfDictService.updateDict(connConfDict);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Response del(@PathVariable("id") String id){
        return connConfDictService.del(id);
    }
}
