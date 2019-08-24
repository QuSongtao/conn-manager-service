/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.*;
import com.suncd.conn.manager.entity.*;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class RecvLogServiceImp implements RecvLogService {
    @Autowired
    private ConnRecvMainHisDao connRecvMainHisDao;

    @Autowired
    private ConnRecvMainDao connRecvMainDao;

    @Autowired
    private ConnRecvMsgDao connRecvMsgDao;

    /**
     * 获取接收日志日志(分页)
     *
     * @param dtStart   接收时间-开始
     * @param dtEnd     接收时间-结束
     * @param telId     电文ID
     * @param senderIn  通信系统编码
     * @param pageIndex 页码
     * @param pageSize  每页显示记录条数
     * @return 接收日志分页数据
     */
    @Override
    public Response getRecvLogData(String dtStart, String dtEnd, String telId, String senderIn, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        String sender = senderIn;
        if(StringUtils.isEmpty(senderIn)){
            sender = null;
        }
        Page<ConnRecvMainHis> page = (Page<ConnRecvMainHis>) connRecvMainHisDao.findByRecvTimeAndTelId(
                dtStart + " 00:00:00", dtEnd + "23:59:59", telId, sender);
        PageResponse<ConnRecvMainHis> pageResponse = new PageResponse<>(page.getTotal(), page.getResult());
        return new Response<>().success(pageResponse);
    }

    @Override
    public Response getRecvMainData(String dtStart, String dtEnd, String telId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<ConnRecvMain> page = (Page<ConnRecvMain>) connRecvMainDao.findByRecvTimeAndTelId(
                dtStart + " 00:00:00", dtEnd + "23:59:59", telId);
        PageResponse<ConnRecvMain> pageResponse = new PageResponse<>(page.getTotal(), page.getResult());
        return new Response<>().success(pageResponse);
    }

    /**
     * 报文重接收处理
     *
     * @param ids 接收日志表主键
     * @return 重发的条数
     */
    @Override
    public Response reDeal(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            // 按id获取接收历史数据记录
            ConnRecvMainHis connRecvMainHis = connRecvMainHisDao.selectByPrimaryKey(id);

            // 组装重新处理的数据
            ConnRecvMain connRecvMain = new ConnRecvMain();
            connRecvMain.setId(UUID.randomUUID().toString());                // 主键
            connRecvMain.setMsgId(connRecvMainHis.getMsgId());               // 消息ID
            connRecvMain.setRecvTime(connRecvMainHis.getRecvTime());         // 记录创建时间,取第一次插入的时间
            connRecvMain.setTelId(connRecvMainHis.getTelId());               // 电文ID
            connRecvMain.setSender(connRecvMainHis.getSender());             // 发送者编码
            connRecvMain.setSenderName(connRecvMainHis.getSenderName());     // 发送者名称
            connRecvMain.setReceiver(connRecvMainHis.getReceiver());         // 接收者编码
            connRecvMain.setReceiverName(connRecvMainHis.getReceiverName()); // 接收者名称
            connRecvMainDao.insertSelective(connRecvMain);
        }
        // 返回处理的记录数量
        return new Response<>().success("成功处理:" + idArr.length + "条记录!");
    }

    /**
     * 获取接收电文的原始数据
     *
     * @param msgId 消息ID
     * @return 组装后的报文原始数据
     */
    @Override
    public Response getTelMessage(String msgId) {
        // 直接按msgId获取消息
        ConnRecvMsg connRecvMsg = connRecvMsgDao.selectByPrimaryKey(msgId);
        return new Response<>().success(connRecvMsg.getMsgTxt());
    }
}
