/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.dao.ConnConfDictDao;
import com.suncd.conn.manager.entity.ConnConfDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConnConfDictServiceImpl implements ConnConfDictService {
    @Autowired
    private ConnConfDictDao connConfDictDao;

    @Override
    public List<ConnConfDict> getByCode(String typeCode) {
//        Map<String, String> ret = new HashMap<>();
        return connConfDictDao.selectByTypeCode(typeCode);
//        for(ConnConfDict c : connConfDicts){
//            ret.put(c.getCode(),c.getName());
//        }
//        return ret;
    }
}
