package com.vivo.uhost.message.model;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class DeviceModel {

    private String emmcId;

    private String pcbCode;

    private String modelVer;  //型号

    private String osVer;  //系统版本

    private String imei; //imei码

    private String serial;//serial

    public String getEmmcId() {
        return emmcId;
    }

    public void setEmmcId(String emmcId) {
        this.emmcId = emmcId;
    }

    public String getPcbCode() {
        return pcbCode;
    }

    public void setPcbCode(String pcbCode) {
        this.pcbCode = pcbCode;
    }

    public String getModelVer() {
        return modelVer;
    }

    public void setModelVer(String modelVer) {
        this.modelVer = modelVer;
    }

    public String getOsVer() {
        return osVer;
    }

    public void setOsVer(String osVer) {
        this.osVer = osVer;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
