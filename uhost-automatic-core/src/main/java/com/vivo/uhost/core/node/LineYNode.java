/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/13 22:17
 * @version 1.0
 * @description
 */
public class LineYNode extends BaseYNode implements Serializable {

    private String name;

    private List<Integer> data;

    public LineYNode() {
    }

    public LineYNode(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
