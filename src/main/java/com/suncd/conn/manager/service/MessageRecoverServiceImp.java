package com.suncd.conn.manager.service;

import com.suncd.conn.manager.dao.ConnConfSyscodeDao;
import com.suncd.conn.manager.dao.ConnConfTelDao;
import com.suncd.conn.manager.dao.ConnRecvMainDao;
import com.suncd.conn.manager.dao.ConnRecvMsgDao;
import com.suncd.conn.manager.entity.ConnConfSyscode;
import com.suncd.conn.manager.entity.ConnConfTel;
import com.suncd.conn.manager.entity.ConnRecvMain;
import com.suncd.conn.manager.entity.ConnRecvMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class MessageRecoverServiceImp implements MessageRecoverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRecoverServiceImp.class);

    @Autowired
    private ConnRecvMainDao connRecvMainDao;
    @Autowired
    private ConnRecvMsgDao connRecvMsgDao;
    @Autowired
    private ConnConfTelDao connConfTelDao;
    @Autowired
    private ConnConfSyscodeDao connConfSyscodeDao;

    @Override
    public void recoverMessage(boolean skType, String message, Map<String, Integer> retMap) {
        String div0 = "//";
        String div1 = "" + (char) 0x7f + (char) 0x26; //分隔符1
        String div2 = "" + (char) 0x1c + (char) 0x26; //分隔符2
        String messageTemp = message.substring(26); // 截取消息内容
        String telId = null;
        String sender = null;
        String msgStr = null;
        // 1. 电文ID解析
        if (skType) {
            telId = messageTemp.substring(0, 4);
            msgStr = messageTemp;
            sender = "SZ";

        } else {
            if (messageTemp.contains(div1)) {
                sender = messageTemp.split(div0)[0];
                msgStr = messageTemp.split(div0)[1];
                telId = msgStr.split(div1)[0];
            } else if (messageTemp.contains(div2)) {
                sender = messageTemp.split(div0)[0];
                msgStr = messageTemp.split(div0)[1];
                telId = msgStr.split(div2)[0];
            }
        }

        if (null == telId || null == sender) {
            LOGGER.warn("消息格式无效,消息内容:{}", messageTemp);
            retMap.put("error", retMap.get("error") + 1);
            return;
        }

        ConnConfTel c = connConfTelDao.selectByTelIdAndSender(telId, sender);
        if (null == c) {
            LOGGER.error("conn_conf_tel中未配置电文ID:{}对应的信息", telId);
            retMap.put("error", retMap.get("error") + 1);
            return;
        }

        // 2. 插入接收消息总表
        String msgId = UUID.randomUUID().toString();
        ConnRecvMsg connRecvMsg = new ConnRecvMsg();
        connRecvMsg.setId(msgId);
        connRecvMsg.setMsgTxt(msgStr);
        connRecvMsg.setCreateTime(new Date());
        connRecvMsgDao.insertSelective(connRecvMsg);

        // 3. 插入接收总表
        ConnRecvMain connRecvMain = new ConnRecvMain();
        connRecvMain.setId(UUID.randomUUID().toString());
        connRecvMain.setMsgId(msgId);
        connRecvMain.setReceiver(c.getReceiver());
        connRecvMain.setReceiverName(getSysNameByCode(c.getReceiver()));
        connRecvMain.setSender(c.getSender());
        connRecvMain.setSenderName(getSysNameByCode(c.getSender()));
        connRecvMain.setTelId(telId);
        connRecvMain.setRecvTime(new Date());
        connRecvMainDao.insertSelective(connRecvMain);
        LOGGER.info("消息恢复完成,telID:{},消息ID:{},消息内容:{}", telId, msgId, msgStr);
        retMap.put("success", retMap.get("success") + 1);
    }

    private String getSysNameByCode(String sysCode) {
        ConnConfSyscode connConfSyscode = connConfSyscodeDao.selectBySysCode(sysCode);
        if (null == connConfSyscode) {
            LOGGER.warn("【警告】通信系统编码:{} 没有在CONN_CONF_SYSCODE表中定义！", sysCode);
            return null;
        } else {
            return connConfSyscode.getSysName();
        }
    }

    public static void main(String[] s) {
        String div1 = "" + (char) 0x7f + (char) 0x26;
        System.out.println(div1);
    }
}
