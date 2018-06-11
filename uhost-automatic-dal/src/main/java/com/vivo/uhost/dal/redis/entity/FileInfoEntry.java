/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.dal.redis.entity;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author huangxiaoqun  2017/11/11 12:38
 * @version 1.0
 * @description
 */
public class FileInfoEntry {


    private static final String cacheExisted = "caex:";

    private static final String netPort = "netport:";


    //管理中心列表
    private static final String ccSite = "ccSite:";

    private static final String pointSite = "potCodes:";

    //管理中心到后台的映射
    private static final String pointSiteMapping = "potCidMp:";

    //管理中心到后台的映射
    private static final String ccSiteMapping = "cidPotMp:";

    //用户报告redis key
    private static final String REPORT_FILE = "rptf:";

    //用户日志redis key
    private static final String USERLOG_FILE = "usrf:";

    public static String buildRptKey() {
        return REPORT_FILE + DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
    }

    public static String buildNetPortKeyCode(String netPortCode) {
        return netPort + netPortCode;
    }

    public static String buildCacheKey() {
        return cacheExisted;
    }

    public static String buildCcIdKey() {
        return ccSite;
    }

    public static String buildPonitSites() {
        return pointSite;
    }

    public static String buildCcMappingKey(String ccId) {
        return ccSiteMapping + ccId;
    }

    public static String buildPointCcidMappingKey(String ponitCode) {
        return pointSiteMapping + ponitCode;
    }

    public static String buildRptKeyByDate(String date) {
        return REPORT_FILE + date;
    }

    public static String buildUsrKey() {
        return USERLOG_FILE + DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
    }

    public static String buildUsrKeyByDate(String date) {
        return USERLOG_FILE + date;
    }


}
