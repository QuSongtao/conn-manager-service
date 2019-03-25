package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnRecvMainHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConnRecvMainHisDao {
    int deleteByPrimaryKey(String id);

    ConnRecvMainHis selectByPrimaryKey(String id);

    List<ConnRecvMainHis> findByRecvTimeAndTelId(@Param("dtStart") String dtStart,
                                                 @Param("dtEnd") String dtEnd,
                                                 @Param("telId") String telId);
}