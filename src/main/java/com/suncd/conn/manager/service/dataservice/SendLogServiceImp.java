/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service.dataservice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.ConnSendMainHisDao;
import com.suncd.conn.manager.entity.ConnSendMainHis;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class SendLogServiceImp implements SendLogService {
    @Autowired
    private ConnSendMainHisDao connSendMainHisDao;

    @Override
    public Response getSendLogData(String dtStart, String dtEnd, String telId,int pageIndex,int pageSize) {
//        Timestamp start = new Timestamp(dtStart.getTime());
//        Timestamp end = new Timestamp(dtEnd.getTime());
        PageHelper.startPage(pageIndex,pageSize);
        Page<ConnSendMainHis> page = (Page<ConnSendMainHis>)connSendMainHisDao.findBySendTimeAndTelId(dtStart,dtEnd,telId);
        PageResponse<ConnSendMainHis> pageResponse = new PageResponse<>(page.getTotal(),page.getResult());
        return new Response<>().success(pageResponse);
    }

    @Override
    public int resend(String ids) {
        return 0;
    }

    @Override
    public Response getTelMessage(String msgId) {
        return null;
    }
}
