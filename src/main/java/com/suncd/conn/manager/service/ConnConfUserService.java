/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.entity.ConnConfUser;
import com.suncd.conn.manager.utils.Response;

public interface ConnConfUserService {
    Response getAll(int pageIndex, int pageSize);
    Response addUser(ConnConfUser connConfUser);
    Response deleteUser(String[] ids,String[] loginNames);
    Response updateUser(ConnConfUser connConfUser);
    Response login(String loginName,String password);

}
