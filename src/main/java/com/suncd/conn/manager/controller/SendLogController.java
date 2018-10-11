/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.dao.SimpleDao;
import com.suncd.conn.manager.service.dataservice.SendLogService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/mgr/sendLog")
public class SendLogController {
    @Autowired
    private SendLogService sendLogService;

    @Autowired
    private SimpleDao simpleDao;
    /**
     * 获取发送日志
     *
     * @param dtStart 发送开始时间
     * @param dtEnd   发送结束时间
     * @param telId   报文ID
     * @return 电文日志记录
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Response getLogData(String  dtStart, String dtEnd, String telId,int pageIndex,int pageSize) {
        try {
            return sendLogService.getSendLogData(dtStart,dtEnd,
//                    new SimpleDateFormat("yyyy-MM-dd").parse(dtStart),
//                    new SimpleDateFormat("yyyy-MM-dd").parse(dtEnd),
                    telId,pageIndex,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Response getLogData(String ids) {
        int ret = sendLogService.resend(ids);
        if (ret > 0) {
            return new Response<>().success("电文重发成功:" + ret + "条记录");
        } else {
            return new Response<>().failure("电文重发失败:" + ret + "条记录");
        }
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

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public Response time() {
        return new Response<>().success(simpleDao.query("select now()"));
    }


}
