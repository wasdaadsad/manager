/**
 *
 */
package com.vivo.uhost.core.domain.rabbit;

import java.util.Date;

/**
 * @author wangtonghuan
 * @date 2016年11月21日
 * @description
 */
public class Model {

    private String id;

    private String name;

    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Model [id=" + id + ", name=" + name + ", time=" + time + "]";
    }
}
