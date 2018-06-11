package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.DeviceProcessDetail;

import java.util.List;

/**
 * @author litingfa  2018/4/24 19:51
 * @version 1.0
 * @description
 */
public interface IDeviceTaskDao {
    void changeTaskState(DeviceProcessDetail deviceProcessDetail);

    List<DeviceProcessDetail> findByRun(Integer msgId, Integer deviceStatusRun);
}
