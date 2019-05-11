/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.suncd.conn.manager.dao.ConnConfUserDao;
import com.suncd.conn.manager.entity.ConnConfUser;
import com.suncd.conn.manager.utils.PageResponse;
import com.suncd.conn.manager.utils.RequestUtil;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
public class ConnConfUserServiceImpl implements ConnConfUserService {
    @Autowired
    private ConnConfUserDao connConfUserDao;

    @Override
    public Response getAll(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<ConnConfUser> page = (Page<ConnConfUser>) connConfUserDao.findAll();
        PageResponse<ConnConfUser> pageResponse = new PageResponse<>(page.getTotal(), page.getResult());
        return new Response<>().success(pageResponse);
    }

    @Override
    public Response addUser(ConnConfUser connConfUser) {
        // 判断用户登录账号是否存在
        if(connConfUserDao.countLoginName(connConfUser.getLoginName()) > 0){
            return new Response<>().failure("用户账号已经存在!");
        }

        connConfUser.setId(UUID.randomUUID().toString());
        int ret = connConfUserDao.insertSelective(connConfUser);
        if (ret >= 0) {
            return new Response<>().success("保存成功!");
        } else {
            return new Response<>().failure("数据保存失败!");
        }
    }

    @Override
    public Response deleteUser(String[] ids,String[] loginNames) {
        if(Arrays.asList(loginNames).contains("admin")){
            return new Response<>().failure("admin用户不允许被删除!");
        }
        int ret  = connConfUserDao.deleteUser(ids);
        if (ret >= 0) {
            return new Response<>().success("删除成功!");
        } else {
            return new Response<>().failure("数据保存失败!");
        }
    }

    /**
     * 只能修改自己的信息
     *
     * @param connConfUser
     * @return
     */
    @Override
    public Response updateUser(ConnConfUser connConfUser) {
        if(connConfUser.getLoginName().equals("admin")){
            ConnConfUser user = connConfUserDao.selectByToken(RequestUtil.currentToken());
            if(!user.getLoginName().equals("admin")){
                return new Response<>().failure("admin用户只能本人修改!");
            }
        }else{
            ConnConfUser user = connConfUserDao.selectByToken(RequestUtil.currentToken());
            if(!user.getLoginName().equals(connConfUser.getLoginName()) && !user.getLoginName().equals("admin") && user.getIsAdmin().equals("0")){
                return new Response<>().failure("普通用户只能修改本人信息!");
            }
        }
        int ret = connConfUserDao.updateByPrimaryKeySelective(connConfUser);
        if (ret >= 0) {
            return new Response<>().success("保存成功!");
        } else {
            return new Response<>().failure("数据保存失败!");
        }
    }

    @Override
    public Response login(String loginName, String password) {
        String token = UUID.randomUUID().toString();
        ConnConfUser connConfUser = connConfUserDao.checkLogin(loginName,password);
        if(null != connConfUser){
            // 更新用户token与登录时间
            connConfUser.setAccessToken(token);
            connConfUser.setLastLoginTime(new Date());
            connConfUserDao.updateByPrimaryKeySelective(connConfUser);
            return new Response<>().success(connConfUser);
        }else {
            return new Response<>().failure("账号或密码错误!");
        }
    }
}
