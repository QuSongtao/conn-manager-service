package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfSyscode;

public interface ConnConfSyscodeDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnConfSyscode record);

    int insertSelective(ConnConfSyscode record);

    ConnConfSyscode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnConfSyscode record);

    int updateByPrimaryKey(ConnConfSyscode record);
}