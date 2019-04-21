/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.entity.ConnConfDict;

import java.util.List;

public interface ConnConfDictService {

    List<ConnConfDict> getByCode(String code);

}
