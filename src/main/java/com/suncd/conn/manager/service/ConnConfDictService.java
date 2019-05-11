/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.entity.ConnConfDict;
import com.suncd.conn.manager.utils.Response;

import java.util.List;

public interface ConnConfDictService {

    List<ConnConfDict> getByCode(String code);
    Response saveDict(ConnConfDict connConfDict);
    Response updateDict(ConnConfDict connConfDict);
    Response del(String id);
}
