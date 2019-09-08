/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.ConnObjectService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mgr/conf")
@Deprecated
public class ConfController {

    @Autowired
    private ConnObjectService connObjectService;

    @RequestMapping(value = "/queue", method = RequestMethod.GET)
    public Response queueConf(int pageIndex, int pageSize) {
        return connObjectService.getObjByType("QUEUE", "S", pageIndex, pageSize);
    }

    @RequestMapping(value = "/channel", method = RequestMethod.GET)
    public Response channelConf(int pageIndex, int pageSize) {
        return connObjectService.getObjByType("CHANNEL", null, pageIndex, pageSize);
    }
}
