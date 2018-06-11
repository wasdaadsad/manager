package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.FlowList;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface IDeviceDao {

    List<DeviceInfo> selectAll();

    void insert(DeviceInfo device);

    void delete(Long id);

    void update(DeviceInfo device);

    List<DeviceInfo> queryList(DeviceInfo device, Pageable pageable);

    Long count(DeviceInfo device);

    List<DeviceInfo> selectListByUhostId(DeviceInfo device);

    List<DeviceInfo> selectListByToken(String token);

    void updateByNodeId(List<DeviceInfo> device);

    void addProcessDetail(DeviceProcessDetail detail);

    List<DeviceProcessDetail> findByDetail(DeviceProcessDetail detail);

    List<DeviceProcessDetail> findByProcess(FlowList flowList, Pageable pageable, Integer status);

    long countDetail(FlowList flowList);

    DeviceInfo selectListByEntity(DeviceInfo deviceInfo);

    void updateState(DeviceInfo deviceInfo);

    List<DeviceProcessDetail> findFailList(DeviceProcessDetail deviceProcessDetail, Pageable pageable);

    DeviceInfo getById(Long deviceId);

    String getMedelVersionById(Long aLong);

    DeviceInfo findByEmecid(String key);

    void updateUhost(DeviceInfo deviceInfo);
}
