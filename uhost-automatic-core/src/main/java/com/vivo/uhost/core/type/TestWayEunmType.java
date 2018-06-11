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
public enum TestWayEunmType {


    TestWay0(0, "T卡升级"),

    TestWay1(1, "fastboot烧写分区--YES"),

    TestWay2(2, "fastboot烧写分区--NO");

    private Integer index;

    private String val;

    TestWayEunmType(Integer index, String val) {
        this.index = index;
        this.val = val;
    }

    public static TestWayEunmType getByIndex(Integer index) {
        for (TestWayEunmType type : TestWayEunmType.values()) {
            if (type.getIndex().equals(index)) {
                return type;
            }
        }
        return null;
    }

    public static TestWayEunmType getByVal(String val) {
        for (TestWayEunmType type : TestWayEunmType.values()) {
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
