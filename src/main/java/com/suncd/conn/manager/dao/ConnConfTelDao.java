package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfTel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConnConfTelDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ConnConfTel record);

    ConnConfTel selectByPrimaryKey(String id);

    ConnConfTel selectByTelIdAndSender(@Param("telId") String telId, @Param("sender") String sender);

    List<ConnConfTel> selectByTelId(String telId);

    int updateByPrimaryKeySelective(ConnConfTel record);

    int updateByPrimaryKey(ConnConfTel record);
}