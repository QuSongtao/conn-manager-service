/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.system.constants.Constant;
import com.suncd.conn.manager.utils.FileUtil;
import com.suncd.conn.manager.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImp implements LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImp.class);
    @Autowired
    private MessageRecoverService messageRecoverService;

    @Override
    public String logContent(String pathIndex, String fileName) {
        File f = new File(findDir(pathIndex) + fileName);
        return FileUtil.readFile(f);
    }

    @Override
    public String logContentNl(String pathIndex, String fileName) {
        File fDir = new File(findDir(pathIndex));
        List<Map<String, String>> listFile = FileUtil.getFiles(fDir);
        // 获取最新的文件
        Map<String, String> newestFile = listFile.get(listFile.size() - 1);
        File f = new File(findDir(pathIndex) + newestFile.get("fileName"));
        return FileUtil.readLastNLineString(f, Constant.LAST_LINES);
    }

    @Override
    public void downloadContent(HttpServletResponse response, String pathIndex, String fileName) {
        File f = new File(findDir(pathIndex) + fileName);
        try {
            FileUtil.downloadFile(response, f);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, String>> getLogFiles(String pathIndex) {
        File f = new File(findDir(pathIndex));
        return FileUtil.getFiles(f);
    }

    @Override
    public Response recoverMessage(String pathIndex, String fileName) {
        File f = new File(findDir(pathIndex) + fileName);
        List<String> messages = FileUtil.readLastNLine(f, -1L);
        if (null == messages) {
            return new Response<>().success("消息文件内容为空!");
        }
        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("success", 0);
        retMap.put("error", 0);
        for (String message : messages) {
            messageRecoverService.recoverMessage(pathIndex.startsWith("SK"), message, retMap);
        }
        return new Response<>().success("成功记录数:" + retMap.get("success") + ", 失败记录数:" + retMap.get("error"));
    }

    private String findDir(String logType) {
        String subFileDir = null;
        switch (logType) {
            case "MQO":
                subFileDir = Constant.MQ_SYS_LOG;
                break;
            case "MQS":
                subFileDir = Constant.MQ_SEND_LOG;
                break;
            case "MQR":
                subFileDir = Constant.MQ_RECV_LOG;
                break;
            case "MQL":
                subFileDir = Constant.MQ_LOSER_LOG;
                break;
            case "SKO":
                subFileDir = Constant.SK_SYS_LOG;
                break;
            case "SKS":
                subFileDir = Constant.SK_SEND_LOG;
                break;
            case "SKR":
                subFileDir = Constant.SK_RECV_LOG;
                break;
            case "SKL":
                subFileDir = Constant.SK_LOSER_LOG;
                break;
            case "MGRO":
                subFileDir = Constant.MGR_SYS_LOG;
                break;
            default:
                break;
        }
        return subFileDir;
    }
}
