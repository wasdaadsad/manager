package com.vivo.uhost.protocol.core.model.mq;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
public class TestItems {

    private List<Long> devicesIDs;

    private List<Item> emmc;

    public List<Long> getDevicesIDs() {
        return devicesIDs;
    }

    public void setDevicesIDs(List<Long> devicesIDs) {
        this.devicesIDs = devicesIDs;
    }

    public List<Item> getEmmc() {
        return emmc;
    }

    public void setEmmc(List<Item> emmc) {
        this.emmc = emmc;
    }

    public List<Item> getCoreBsp() {
        return coreBsp;
    }

    public void setCoreBsp(List<Item> coreBsp) {
        this.coreBsp = coreBsp;
    }

    public List<Item> getFsTf() {
        return fsTf;
    }

    public void setFsTf(List<Item> fsTf) {
        this.fsTf = fsTf;
    }
    @JsonProperty("coreBsp")
    private List<Item> coreBsp;
    private List<Item> fsTf;

    @Override
    public String toString() {
        return "TestItems{" +
                "devicesIDs=" + devicesIDs +
                ", emmc=" + emmc +
                ", coreBsp=" + coreBsp +
                ", fsTf=" + fsTf +
                ", testCode='" + testCode + '\'' +
                '}';
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    private String testCode;

}
