/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface LogService {
    String logContent(String pathIndex, String fileName);
    String logContentNl(String pathIndex, String fileName);
    void downloadContent(HttpServletResponse response, String pathIndex, String fileName);
    List<Map<String ,String >> getLogFiles(String pathIndex);
}
