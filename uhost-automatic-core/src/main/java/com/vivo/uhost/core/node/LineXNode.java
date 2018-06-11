/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/13 22:16
 * @version 1.0
 * @description
 */
public class LineXNode implements Serializable {

    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
