/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.dao.ConnConfSyscodeDao;
import com.suncd.conn.manager.entity.ConnConfSyscode;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConnConfSyscodeServiceImpl implements ConnConfSyscodeService {
    @Autowired
    private ConnConfSyscodeDao connConfSyscodeDao;

    @Override
    public Response getAll() {
        return new Response<>().success(connConfSyscodeDao.findAll());
    }

    @Override
    public Response changeStatus(String id, String value) {
        ConnConfSyscode connConfSyscode = new ConnConfSyscode();
        connConfSyscode.setId(id);
        connConfSyscode.setConnFlag(value);

        int ret = connConfSyscodeDao.updateByPrimaryKeySelective(connConfSyscode);
        if (ret >= 0) {
            return new Response<>().success("操作成功!");
        } else {
            return new Response<>().failure("操作失败!");
        }
    }
}
