package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnRecvMain;

public interface ConnRecvMainDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ConnRecvMain record);

    int countAll();

    ConnRecvMain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnRecvMain record);
}