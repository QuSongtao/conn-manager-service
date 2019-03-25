package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnSendMainHis;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ConnSendMainHisDao {

    ConnSendMainHis selectByPrimaryKey(String id);

    List<ConnSendMainHis> findBySendTimeAndTelId(@Param("dtStart") String dtStart,
                                                 @Param("dtEnd") String dtEnd,
                                                 @Param("telId") String telId);
}