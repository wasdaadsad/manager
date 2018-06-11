package com.vivo.uhost.service;

import java.util.Map;

/**
 * @author litingfa  2018/3/30 14:37
 * @version 1.0
 * @description
 */
public interface IDeviceTaskService {
    void changeTaskState(Integer msgId, String token, Map<String, Integer> resultMap);
}
