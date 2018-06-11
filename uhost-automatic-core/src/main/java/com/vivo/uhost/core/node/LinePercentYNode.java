/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/17 14:54
 * @version 1.0
 * @description
 */
public class LinePercentYNode extends BaseYNode implements Serializable {

    private String name;

    private List<Float> data;

    public LinePercentYNode() {
    }

    public LinePercentYNode(String name, List<Float> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }
}
