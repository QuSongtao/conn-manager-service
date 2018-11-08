/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.ConnConfObjectDao;
import com.suncd.conn.manager.entity.ConnConfObject;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnObjectServiceImpl implements ConnObjectService {
    @Autowired
    private ConnConfObjectDao connConfObjectDao;

    @Override
    public Response getObjByType(String objType, String transferType,int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<ConnConfObject> connConfObjects = ( Page<ConnConfObject>)connConfObjectDao.selectByType(objType, transferType);
        PageResponse<ConnConfObject> pageResponse = new PageResponse<>(connConfObjects.getTotal(),connConfObjects.getResult());
        return new Response<>().success(pageResponse);
    }
}
