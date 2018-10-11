package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfTel;

public interface ConnConfTelDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnConfTel record);

    int insertSelective(ConnConfTel record);

    ConnConfTel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnConfTel record);

    int updateByPrimaryKey(ConnConfTel record);
}