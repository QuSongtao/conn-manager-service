/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.utils.Response;

public interface ConnConfSyscodeService {
    Response getAll();
    Response changeStatus(String id, String value);
}
