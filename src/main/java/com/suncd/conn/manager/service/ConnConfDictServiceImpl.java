/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.dao.ConnConfDictDao;
import com.suncd.conn.manager.entity.ConnConfDict;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnConfDictServiceImpl implements ConnConfDictService {
    @Autowired
    private ConnConfDictDao connConfDictDao;

    @Override
    public List<ConnConfDict> getByCode(String typeCode) {
        return connConfDictDao.selectByTypeCode(typeCode);
    }

    @Override
    public Response saveDict(ConnConfDict connConfDict) {
        int ret = connConfDictDao.insertSelective(connConfDict);
        if(ret >= 0){
            return new Response<>().success("保存成功!");
        }else{
            return new Response<>().failure("保存失败!");
        }
    }

    @Override
    public Response updateDict(ConnConfDict connConfDict) {
        int ret = connConfDictDao.updateByPrimaryKeySelective(connConfDict);
        if(ret >= 0){
            return new Response<>().success("保存成功!");
        }else{
            return new Response<>().failure("保存失败!");
        }
    }

    @Override
    public Response del(String id) {
        int ret = connConfDictDao.deleteByPrimaryKey(id);
        if(ret >= 0){
            return new Response<>().success("删除成功!");
        }else{
            return new Response<>().failure("删除失败!");
        }
    }
}
