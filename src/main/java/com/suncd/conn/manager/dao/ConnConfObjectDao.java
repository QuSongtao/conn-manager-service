package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfObject;

import java.util.List;

public interface ConnConfObjectDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnConfObject record);

    int insertSelective(ConnConfObject record);

    ConnConfObject selectByPrimaryKey(String id);

    List<ConnConfObject> selectByType(String objType, String transferType);

    int updateByPrimaryKeySelective(ConnConfObject record);

    int updateByPrimaryKey(ConnConfObject record);
}