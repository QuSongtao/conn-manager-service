/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.dao.ConnRecvMainDao;
import com.suncd.conn.manager.dao.ConnSendMainDao;
import com.suncd.conn.manager.dao.ConnTotalNumDao;
import com.suncd.conn.manager.entity.ConnTotalNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountServiceImp implements CountService {
    @Autowired
    private ConnSendMainDao connSendMainDao;
    @Autowired
    private ConnRecvMainDao connRecvMainDao;
    @Autowired
    private ConnTotalNumDao connTotalNumDao;

    @Override
    public Map count() {
        Map<String ,Object > map = new HashMap<>();
        int toSendNum = connSendMainDao.countAll(); // 待发送记录数量
        int toRecvNum = connRecvMainDao.countAll(); // 待业务处理记录数量
        map.put("toSendNum",toSendNum);
        map.put("toRecvNum",toRecvNum);
        List<ConnTotalNum> connTotalNums = connTotalNumDao.selectAll();
        for(ConnTotalNum connTotalNum : connTotalNums){
            if(connTotalNum.getType().equals("SS")){
                map.put("sendTotalNum",connTotalNum.getTotalNum());
            }else if(connTotalNum.getType().equals("RR")){
                map.put("recvTotalNum",connTotalNum.getTotalNum());
            }
        }
        return map;
    }
}
