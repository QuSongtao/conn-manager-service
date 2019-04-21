package com.suncd.conn.manager.dao;

import com.suncd.conn.manager.entity.ConnConfUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConnConfUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ConnConfUser record);

    int insertSelective(ConnConfUser record);

    ConnConfUser selectByPrimaryKey(Integer id);

    ConnConfUser selectByToken(String  accessToken);

    int countLoginName(String loginName);

    ConnConfUser checkLogin(@Param("loginName") String loginName, @Param("password") String password);

    int deleteUser(String[] ids);

    List<ConnConfUser> findAll();

    int updateByPrimaryKeySelective(ConnConfUser record);

    int updateByPrimaryKey(ConnConfUser record);
}