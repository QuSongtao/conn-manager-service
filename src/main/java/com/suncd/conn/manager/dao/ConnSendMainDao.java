package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnSendMain;

public interface ConnSendMainDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ConnSendMain record);

    int countAll();

    ConnSendMain selectByPrimaryKey(String id);

}