package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfUser;

public interface ConnConfUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ConnConfUser record);

    int insertSelective(ConnConfUser record);

    ConnConfUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConnConfUser record);

    int updateByPrimaryKey(ConnConfUser record);
}