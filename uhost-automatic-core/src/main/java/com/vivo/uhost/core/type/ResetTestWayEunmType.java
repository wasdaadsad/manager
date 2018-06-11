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
public enum ResetTestWayEunmType {


    TestWay0(0, "广播升级"),

    TestWay1(1, "指令升级");

    private Integer index;

    private String val;

    ResetTestWayEunmType(Integer index, String val) {
        this.index = index;
        this.val = val;
    }

    public static ResetTestWayEunmType getByIndex(Integer index) {
        for (ResetTestWayEunmType type : ResetTestWayEunmType.values()) {
            if (type.getIndex().equals(index)) {
                return type;
            }
        }
        return null;
    }

    public static ResetTestWayEunmType getByVal(String val) {
        for (ResetTestWayEunmType type : ResetTestWayEunmType.values()) {
            if (type.getVal().equals(val)) {
                return type;
            }
        }
        return null;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
