package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfTel;

import java.util.List;

public interface ConnConfTelDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ConnConfTel record);

    ConnConfTel selectByPrimaryKey(String id);

    List<ConnConfTel> selectByTelId(String TelId);

    int updateByPrimaryKeySelective(ConnConfTel record);

    int updateByPrimaryKey(ConnConfTel record);
}