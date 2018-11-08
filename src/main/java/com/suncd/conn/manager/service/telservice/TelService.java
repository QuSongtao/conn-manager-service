package com.suncd.conn.manager.service.telservice;

import com.suncd.conn.manager.entity.ConnConfTel;
import com.suncd.conn.manager.utils.Response;

public interface TelService {
    Response getTelConfig(String telId, int pageIndex, int pageSize);

    int updateTel(ConnConfTel connConfTel);

    int saveTel(ConnConfTel connConfTel);

    int deleteById(String id);

    Response getTelById(String id);
}
