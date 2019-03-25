/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.ConnSendMainDao;
import com.suncd.conn.manager.dao.ConnSendMainHisDao;
import com.suncd.conn.manager.dao.ConnSendMsgDao;
import com.suncd.conn.manager.entity.ConnSendMain;
import com.suncd.conn.manager.entity.ConnSendMainHis;
import com.suncd.conn.manager.entity.ConnSendMsg;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SendLogServiceImp implements SendLogService {
    @Autowired
    private ConnSendMainHisDao connSendMainHisDao;

    @Autowired
    private ConnSendMainDao connSendMainDao;

    @Autowired
    private ConnSendMsgDao connSendMsgDao;

    /**
     * 获取发送日志(分页)
     *
     * @param dtStart   发送时间-开始
     * @param dtEnd     发送时间-结束
     * @param telId     电文ID
     * @param pageIndex 页码
     * @param pageSize  每页显示记录条数
     * @return 发送日志分页数据
     */
    @Override
    public Response getSendLogData(String dtStart, String dtEnd, String telId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<ConnSendMainHis> page = (Page<ConnSendMainHis>) connSendMainHisDao.findBySendTimeAndTelId(
                dtStart + " 00:00:00", dtEnd + "23:59:59", telId);
        PageResponse<ConnSendMainHis> pageResponse = new PageResponse<>(page.getTotal(), page.getResult());
        return new Response<>().success(pageResponse);
    }

    /**
     * 报文重发逻辑处理
     *
     * @param ids 发送日志表主键
     * @return 重发的条数
     */
    @Override
    public Response resend(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            // 按id获取发送历史数据记录
            ConnSendMainHis connSendMainHis = connSendMainHisDao.selectByPrimaryKey(id);

            // 组装重发数据
            ConnSendMain connSendMain = new ConnSendMain();
            connSendMain.setId(UUID.randomUUID().toString());                // 主键
            connSendMain.setMsgId(connSendMainHis.getMsgId());               // 消息ID
            connSendMain.setCreateTime(connSendMainHis.getCreateTime());     // 记录创建时间,取第一次插入的时间
            connSendMain.setTelId(connSendMainHis.getTelId());               // 电文ID
            connSendMain.setSender(connSendMainHis.getSender());             // 发送者编码
            connSendMain.setSenderName(connSendMainHis.getSenderName());     // 发送者名称
            connSendMain.setReceiver(connSendMainHis.getReceiver());         // 接收者编码
            connSendMain.setReceiverName(connSendMainHis.getReceiverName()); // 接收者名称
            connSendMainDao.insertSelective(connSendMain);
        }
        // 返回处理的记录数量
        return new Response<>().success("成功处理:" + idArr.length + "条记录!");
    }

    /**
     * 获取发送电文的原始数据
     *
     * @param msgId 消息ID
     * @return 组装后的报文原始数据
     */
    @Override
    public Response getTelMessage(String msgId) {
        // 直接按msgId获取消息
        ConnSendMsg connSendMsg = connSendMsgDao.selectByPrimaryKey(msgId);
        return new Response<>().success(connSendMsg.getMsgTxt());
    }
}
