package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.ProcessDetail;

import java.util.List;

/**
 * @author litingfa  2018/4/8 14:49
 * @version 1.0
 * @description
 */
public interface IDeviceProcessDetailDao {
    List<DeviceProcessDetail> findByPid(String pId);
}
