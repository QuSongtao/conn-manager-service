package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfObject;

public interface ConnConfObjectDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnConfObject record);

    int insertSelective(ConnConfObject record);

    ConnConfObject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnConfObject record);

    int updateByPrimaryKey(ConnConfObject record);
}