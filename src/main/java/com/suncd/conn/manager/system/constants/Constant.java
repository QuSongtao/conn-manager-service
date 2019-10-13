/*
成都太阳高科技有限责任公司
http://www.suncd.com
*/
package com.suncd.conn.manager.system.constants;

public class Constant {

    private Constant(){

    }

    // ------------------------------------ CONSTANTS ------------------------------------
    // 队列管理器状态 0-停止 1-运行
    public static int QMGR_STATUS = 0;


    // 日志目录
    public static final String MQ_SEND_LOG = "logs/mq/sendLog/";
    public static final String MQ_RECV_LOG = "logs/mq/recvLog/";
    public static final String MQ_SYS_LOG = "logs/mq/sysLog/";
    public static final String MQ_LOSER_LOG = "logs/mq/loserLog/";
    public static final String SK_SEND_LOG = "logs/socket/sendLog/";
    public static final String SK_RECV_LOG = "logs/socket/recvLog/";
    public static final String SK_SYS_LOG = "logs/socket/sysLog/";
    public static final String SK_LOSER_LOG = "logs/socket/loserLog/";
    public static final String MGR_SYS_LOG = "logs/mgr/sysLog/";

    // 读取文件最后N行
    public static final long LAST_LINES = 80L;

}
