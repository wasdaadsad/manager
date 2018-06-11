/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.client;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author huangxiaoqun  2018/3/21 19:18
 * @version 1.0
 * @description
 */
public class CfgUtils {


    private static Properties properties = new Properties();

    static {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/vivo.properties"), StandardCharsets.UTF_8));
            properties.load(in);
        } catch (Exception ex) {
            throw new RuntimeException("load config vivo.properties failed!", ex);
        }
    }

    /**
     * 获取字符串的值
     *
     * @param key          key
     * @param defaultValue 默认值
     * @return 结果
     */
    public static String getString(String key, String defaultValue) {
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return (String) properties.get(key);
    }


    /**
     * 获取配置 ,先从配置文件中获取，如果没有的话则从系统环境变量中读取
     *
     * @param key key
     * @return 配置信息
     */
    public static String getConfig(String key) {
        String envValue = (String) properties.get(key);
        if (StringUtils.isEmpty(envValue)) {
            return System.getenv(key);
        }
        return envValue;
    }


    /**
     * 获取Integer的值
     *
     * @param key          key
     * @param defaultValue 默认值
     * @return 结果
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return Integer.parseInt((String) properties.get(key));
    }


}
