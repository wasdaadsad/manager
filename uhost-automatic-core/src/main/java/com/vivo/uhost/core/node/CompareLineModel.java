/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.util.List;

/**
 * @author huangxiaoqun  2017/11/16 22:20
 * @version 1.0
 * @description
 */
public class CompareLineModel {

    private List<String> categories;

    private List<LineYNode> yNodes;

    public CompareLineModel() {
    }

    public CompareLineModel(List<String> categories, List<LineYNode> yNodes) {
        this.categories = categories;
        this.yNodes = yNodes;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<LineYNode> getyNodes() {
        return yNodes;
    }

    public void setyNodes(List<LineYNode> yNodes) {
        this.yNodes = yNodes;
    }
}
