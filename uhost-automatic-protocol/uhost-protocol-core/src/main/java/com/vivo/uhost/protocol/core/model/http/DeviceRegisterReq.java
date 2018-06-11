package com.vivo.uhost.protocol.core.model.http;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class DeviceRegisterReq {

    private String emmcId;
    private String pcbCode;
    private String modelVer;  //型号
    private String osVer;  //系统版本

    public DeviceRegisterReq() {
    }

    public DeviceRegisterReq(String emmcId, String pcbCode, String modelVer, String osVer) {
        this.emmcId = emmcId;
        this.pcbCode = pcbCode;
        this.modelVer = modelVer;
        this.osVer = osVer;
    }

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

    @Override
    public String toString() {
        return "DeviceRegisterReq{" +
                "emmcId='" + emmcId + '\'' +
                ", pcbCode='" + pcbCode + '\'' +
                ", modelVer='" + modelVer + '\'' +
                ", osVer='" + osVer + '\'' +
                '}';
    }
}
