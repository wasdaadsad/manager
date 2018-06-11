/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

/**
 * @author huangxiaoqun  2017/11/13 22:13
 * @version 1.0
 * @description
 */
public class ColumnNode {


    private String name;

    private Integer y;

    private String drilldown;

    public ColumnNode() {
    }

    public ColumnNode(String name, Integer y, String drilldown) {
        this.name = name;
        this.y = y;
        this.drilldown = drilldown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDrilldown() {
        return drilldown;
    }

    public void setDrilldown(String drilldown) {
        this.drilldown = drilldown;
    }
}
