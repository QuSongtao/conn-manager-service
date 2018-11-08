/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.service;

import com.suncd.conn.manager.system.constants.Constant;
import com.suncd.conn.manager.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImp implements LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImp.class);
    @Override
    public String logContent(String pathIndex, String fileName ) {
        File f = new File(findDir(pathIndex) + fileName);
        return FileUtil.readFile(f);
    }

    @Override
    public String logContentNl(String pathIndex, String fileName) {
        File f = new File(findDir(pathIndex) + fileName);
        return FileUtil.readLastNLineString(f,Constant.LAST_LINES);
    }

    @Override
    public void downloadContent(HttpServletResponse response, String pathIndex, String fileName ) {
        File f = new File(findDir(pathIndex) + fileName);
        try {
            FileUtil.downloadFile(response,f);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    @Override
    public List<Map<String, String>> getLogFiles(String pathIndex) {
        File f = new File(findDir(pathIndex));
        return FileUtil.getFiles(f);
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
            case "SKO":
                subFileDir = Constant.SK_SYS_LOG;
                break;
            case "SKS":
                subFileDir = Constant.SK_SEND_LOG;
                break;
            case "SKR":
                subFileDir = Constant.SK_RECV_LOG;
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
