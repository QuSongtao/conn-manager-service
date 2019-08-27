package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.RecvLogService;
import com.suncd.conn.manager.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mgr/recvLog")
public class RecvLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecvLogController.class);
    @Autowired
    private RecvLogService recvLogService;

    /**
     * 获取接收历史表日志
     *
     * @param dtStart 接收开始时间
     * @param dtEnd   接收结束时间
     * @param telId   报文ID
     * @return 电文日志记录
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Response getLogData(String dtStart, String dtEnd, String telId, String sender, String dealFlag, int pageIndex, int pageSize) {
        try {
            return recvLogService.getRecvLogData(dtStart, dtEnd, telId, sender, dealFlag, pageIndex, pageSize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取接收总表日志
     *
     * @param dtStart 接收开始时间
     * @param dtEnd   接收结束时间
     * @param telId   报文ID
     * @return 电文日志记录
     */
    @RequestMapping(value = "/mainData", method = RequestMethod.GET)
    public Response getLogMainData(String dtStart, String dtEnd, String telId, int pageIndex, int pageSize) {
        try {
            return recvLogService.getRecvMainData(dtStart, dtEnd, telId, pageIndex, pageSize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 电文重新处理
     *
     * @param ids 记录ids主键,以逗号隔开
     * @return 影响的条数
     */
    @RequestMapping(value = "/reDeal", method = RequestMethod.POST)
    public Response reDeal(String ids) {
        return recvLogService.reDeal(ids);
    }

    /**
     * 根据消息ID查找消息内容
     *
     * @param msgId 消息ID
     * @return 消息内容
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Response getTelMessage(String msgId) {
        return recvLogService.getTelMessage(msgId);
    }
}
