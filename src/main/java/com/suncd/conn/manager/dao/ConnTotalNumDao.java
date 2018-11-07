package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnTotalNum;

import java.util.List;

public interface ConnTotalNumDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnTotalNum record);

    int insertSelective(ConnTotalNum record);

    ConnTotalNum selectByPrimaryKey(String id);

    List<ConnTotalNum> selectAll();

    int updateByPrimaryKeySelective(ConnTotalNum record);

    int updateByPrimaryKey(ConnTotalNum record);
}