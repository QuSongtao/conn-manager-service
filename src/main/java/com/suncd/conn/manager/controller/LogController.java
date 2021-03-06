/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.controller;

import com.suncd.conn.manager.service.LogService;
import com.suncd.conn.manager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgr/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 获取文件列表
     *
     * @param pathIndex 路径索引
     * @return 文件列表
     */
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public List<Map<String, String>> getLogFiles(String pathIndex) {
        return logService.getLogFiles(pathIndex);
    }

    /**
     * 读取文件最后N行
     *
     * @return 文件内容
     */
    @RequestMapping(value = "/lastNLines", method = RequestMethod.GET)
    public String lastNLines(String pathIndex, String fileName) {
        return logService.logContentNl(pathIndex, fileName);
    }

    /**
     * 读取整个文件内容
     *
     * @return 文件内容
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String content(String pathIndex, String fileName) {
        return logService.logContent(pathIndex, fileName);
    }

    /**
     * 文件下载
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, String pathIndex, String fileName) {
        logService.downloadContent(response, pathIndex, fileName);
    }

    /**
     * 丢失消息恢复
     *
     * @return
     */
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public Response recover(String pathIndex, String fileName) {
        return logService.recoverMessage(pathIndex, fileName);
    }

}
