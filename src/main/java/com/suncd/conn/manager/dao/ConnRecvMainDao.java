package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnRecvMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConnRecvMainDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ConnRecvMain record);

    int countAll();

    ConnRecvMain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnRecvMain record);

    List<ConnRecvMain> findByRecvTimeAndTelId(@Param("dtStart") String dtStart,
                                              @Param("dtEnd") String dtEnd,
                                              @Param("telId") String telId);
}