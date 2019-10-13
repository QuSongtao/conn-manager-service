package com.suncd.conn.manager.service;

import java.util.Map;

public interface MessageRecoverService {
    void recoverMessage(boolean skType, String message, Map<String,Integer> retMap);
}
