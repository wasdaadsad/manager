/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.type;

/**
 * @author huangxiaoqun  2017/12/1 10:09
 * @version 1.0
 * @description
 */
public enum ReportType {

    InnerReort(0, "内销报告"),

    OutterReort(1, "外销报告");

    ReportType(Integer index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    private Integer index;

    private String remark;


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
