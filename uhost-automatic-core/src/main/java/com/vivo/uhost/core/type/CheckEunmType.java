/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.type;

/**
 * @author huangxiaoqun  2017/11/22 21:29
 * @version 1.0
 * @description
 */
public enum CheckEunmType {


    Ok(0, 128, "很好，对售后有很大帮助"),

    Inconvenient(1, 64, "界面布局或操作不方便"),

    Collapse(2, 32, "SDA死机，崩溃"),

    UnReasonable(3, 16, "检测项规划不合理"),

    CheckTimeOut(4, 8, "检测时间太长"),

    CheckError(5, 4, "检测不准"),

    ConnectFailed(6, 2, "无法连接手机"),

    ConnectLong(7, 1, "连接很慢，时间长");


    private Integer index;

    private Integer val;

    private String remark;

    CheckEunmType(Integer index, Integer val, String remark) {
        this.index = index;
        this.val = val;
        this.remark = remark;
    }

    public static CheckEunmType getByIndex(Integer val) {
        for (CheckEunmType type : CheckEunmType.values()) {
            if (type.getIndex().equals(val)) {
                return type;
            }
        }
        return null;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
