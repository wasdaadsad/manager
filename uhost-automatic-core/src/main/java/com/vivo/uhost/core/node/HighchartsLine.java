/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/16 15:23
 * @version 1.0
 * @description
 */
public class HighchartsLine implements Serializable {

    private List<String> categories;

    private List<Integer> data;

    public HighchartsLine() {
    }

    public HighchartsLine(List<String> categories, List<Integer> data) {
        this.categories = categories;
        this.data = data;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
