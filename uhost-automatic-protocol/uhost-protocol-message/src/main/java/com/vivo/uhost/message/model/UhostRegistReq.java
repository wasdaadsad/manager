/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

/**
 * @author huangxiaoqun  2018/3/21 11:42
 * @version 1.0
 * @description
 */
public class UhostRegistReq {

    //身份标识
    private String uidentify;

    //ip地址
    private String ipAdress;


    public String getUidentify() {
        return uidentify;
    }

    public void setUidentify(String uidentify) {
        this.uidentify = uidentify;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public UhostRegistReq() {
    }

    public UhostRegistReq(String uidentify, String ipAdress) {
        this.uidentify = uidentify;
        this.ipAdress = ipAdress;
    }
}
