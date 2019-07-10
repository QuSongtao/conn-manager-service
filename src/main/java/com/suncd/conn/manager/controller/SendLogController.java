/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.SendLogService;
import com.suncd.conn.manager.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mgr/sendLog")
public class SendLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendLogController.class);
    @Autowired
    private SendLogService sendLogService;

    /**
     * 获取发送日志
     *
     * @param dtStart 发送开始时间
     * @param dtEnd   发送结束时间
     * @param telId   报文ID
     * @return 电文日志记录
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Response getLogData(String dtStart, String dtEnd, String telId, int pageIndex, int pageSize) {
        try {
            return sendLogService.getSendLogData(dtStart, dtEnd, telId, pageIndex, pageSize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 电文重新发送
     *
     * @param ids 记录ids主键,以逗号隔开
     * @return 重发影响的条数
     */
    @RequestMapping(value = "/resend", method = RequestMethod.POST)
    public Response resend(String ids) {
        return sendLogService.resend(ids);
    }

    /**
     * 根据消息ID查找消息内容
     *
     * @param msgId 消息ID
     * @return 消息内容
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Response getTelMessage(String msgId) {
        return sendLogService.getTelMessage(msgId);
    }
}
