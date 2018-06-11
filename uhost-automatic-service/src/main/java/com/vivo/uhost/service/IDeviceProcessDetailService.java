package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.ProcessDetail;

import java.util.List;

/**
 * @author litingfa  2018/4/8 14:48
 * @version 1.0
 * @description
 */
public interface IDeviceProcessDetailService {
    List<DeviceProcessDetail> findByPid(String s);
}
