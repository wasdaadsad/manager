/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/17 14:57
 * @version 1.0
 * @description
 */
public class ComparePercentLineModel implements Serializable {

    private List<String> categories;

    private List<LinePercentYNode> yNodes;

    public ComparePercentLineModel() {
    }

    public ComparePercentLineModel(List<String> categories, List<LinePercentYNode> yNodes) {
        this.categories = categories;
        this.yNodes = yNodes;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<LinePercentYNode> getyNodes() {
        return yNodes;
    }

    public void setyNodes(List<LinePercentYNode> yNodes) {
        this.yNodes = yNodes;
    }
}
