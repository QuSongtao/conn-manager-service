/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.entity.ConnConfTel;
import com.suncd.conn.manager.service.telservice.TelService;
import com.suncd.conn.manager.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mgr/tel")
public class TelConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelConfigController.class);
    @Autowired
    private TelService telService;

    /**
     * 按telId模糊查询电文id配置信息
     *
     * @param telId     电文id
     * @param pageIndex 分页页码
     * @param pageSize  每页大小
     * @return 电文信息
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getTelByTelId(String telId, int pageIndex, int pageSize) {
        try {
            return telService.getTelConfig(telId, pageIndex, pageSize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 更新电文配置
     *
     * @param connConfTel 电文信息
     * @return response
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public Response update(ConnConfTel connConfTel) {
        int k = telService.updateTel(connConfTel);
        if (k >= 0) {
            return new Response<>().success();
        } else {
            return new Response<>().failure("数据更新失败!");
        }
    }

    /**
     * 增加电文配置
     *
     * @param connConfTel 电文信息
     * @return response
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response save(ConnConfTel connConfTel) {
        int k = telService.saveTel(connConfTel);
        if (k >= 0) {
            return new Response<>().success();
        } else {
            return new Response<>().failure("数据保存失败!");
        }
    }

    /**
     * 按id删除电文配置
     *
     * @param id 记录id主键,以逗号隔开
     * @return 重发影响的条数
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Response resend(String id) {
        if (telService.deleteById(id) >= 0) {
            return new Response<>().success();
        } else {
            return new Response<>().failure("删除失败!");
        }
    }

    /**
     * 根据消息ID查找报文信息
     *
     * @param id 消息ID
     * @return 消息内容
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response getTelMessage(@PathVariable("id") String id) {
        return telService.getTelById(id);
    }

}
