/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

/**
 * @author huangxiaoqun  2017/11/20 11:26
 * @version 1.0
 * @description
 */
public class PieNodeData {

    private String name;

    private float y;

    private boolean sliced;

    private boolean selected;

    public PieNodeData() {
    }

    public PieNodeData(String name, float y) {
        this.name = name;
        this.y = y;
    }

    public PieNodeData(String name, float y, boolean sliced, boolean selected) {
        this.name = name;
        this.y = y;
        this.sliced = sliced;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isSliced() {
        return sliced;
    }

    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
