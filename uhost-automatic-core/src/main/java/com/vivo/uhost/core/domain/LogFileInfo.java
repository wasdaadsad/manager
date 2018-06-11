/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.domain;

import java.io.File;
import java.util.Date;

/**
 * @author huangxiaoqun  2017/9/1 9:02
 * @version 1.0
 * @description
 */
public class LogFileInfo {

    //外部代号
    private File file;

    //imei编号
    private String imei;

    //创建日期
    private Date createDate;

    public LogFileInfo() {
    }

    public LogFileInfo(File file, String imei, Date createDate) {
        this.file = file;
        this.imei = imei;
        this.createDate = createDate;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
