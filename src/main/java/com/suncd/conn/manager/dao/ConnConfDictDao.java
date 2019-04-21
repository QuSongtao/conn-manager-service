package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfDict;

import java.util.List;

public interface ConnConfDictDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnConfDict record);

    int insertSelective(ConnConfDict record);

    ConnConfDict selectByPrimaryKey(String id);

    List<ConnConfDict> selectByTypeCode(String typeCode);

    int updateByPrimaryKeySelective(ConnConfDict record);

    int updateByPrimaryKey(ConnConfDict record);
}