/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.suncd.conn.manager.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 *
 * @author qust
 * @version 1.0 2018/4/16 15:08
 */
public class RequestUtil {
    private RequestUtil() {
    }

    public static HttpServletRequest currentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String currentToken(){
        return currentRequest().getHeader("accessToken");
    }
}
