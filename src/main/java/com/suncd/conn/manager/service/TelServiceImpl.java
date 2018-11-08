/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.ConnConfTelDao;
import com.suncd.conn.manager.entity.ConnConfTel;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TelServiceImpl implements TelService {
    @Autowired
    private ConnConfTelDao connConfTelDao;

    @Override
    public Response getTelConfig(String telId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<ConnConfTel> page = (Page<ConnConfTel>) connConfTelDao.selectByTelId(telId);
        PageResponse<ConnConfTel> pageResponse = new PageResponse<>(page.getTotal(), page.getResult());
        return new Response<>().success(pageResponse);
    }

    @Override
    public int updateTel(ConnConfTel connConfTel) {
        return connConfTelDao.updateByPrimaryKeySelective(connConfTel);
    }

    @Override
    public int saveTel(ConnConfTel connConfTel) {
        connConfTel.setId(UUID.randomUUID().toString());
        return connConfTelDao.insertSelective(connConfTel);
    }

    @Override
    public int deleteById(String id) {
        return connConfTelDao.deleteByPrimaryKey(id);
    }

    @Override
    public Response getTelById(String id) {
        return null;
    }
}
