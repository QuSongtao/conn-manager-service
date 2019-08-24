/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.utils.Response;

public interface RecvLogService {
    Response getRecvLogData(String dtStart, String dtEnd, String telId, String sender, int pageIndex, int pageSize);
    Response getRecvMainData(String dtStart, String dtEnd, String telId, int pageIndex, int pageSize);
    Response reDeal(String ids);
    Response getTelMessage(String msgId);
}
