/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost;

import com.bbkmobile.iqoo.common.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangxiaoqun  2018/1/16 21:04
 * @version 1.0
 * @description
 */
public class MyTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("vivo123");
        list.add("vivo234");
        String json = JsonUtil.bean2Json(list);
        List<String> list1 = JsonUtil.json2Bean(json,List.class,String.class);
        System.out.println(list1.size());
    }

}
