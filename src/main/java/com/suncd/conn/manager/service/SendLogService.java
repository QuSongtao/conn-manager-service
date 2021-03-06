/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.utils.Response;

import java.util.Date;

public interface SendLogService {
    Response getSendLogData(String dtStart, String dtEnd, String telId, String receiverIn, int pageIndex, int pageSize);

    Response resend(String ids);

    Response getTelMessage(String msgId);
}
