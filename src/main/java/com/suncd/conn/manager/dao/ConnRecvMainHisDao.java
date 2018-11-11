package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnRecvMainHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConnRecvMainHisDao {
    int deleteByPrimaryKey(String id);

    int insert(ConnRecvMainHis record);

    int insertSelective(ConnRecvMainHis record);

    ConnRecvMainHis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConnRecvMainHis record);

    int updateByPrimaryKey(ConnRecvMainHis record);

    List<ConnRecvMainHis> findByRecvTimeAndTelId(@Param("dtStart") String dtStart,
                                                 @Param("dtEnd") String dtEnd,
                                                 @Param("telId") String telId);
}