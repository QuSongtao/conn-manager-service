package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnSendMain;

public interface ConnSendMainDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnSendMain record);

    int insertSelective(ConnSendMain record);

    ConnSendMain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnSendMain record);

    int updateByPrimaryKey(ConnSendMain record);
}