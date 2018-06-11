package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.FlowList;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface IDeviceService {
    /**
     * 查询所有device
     *
     * @return
     */
    List<DeviceInfo> findAll();

    List<DeviceInfo> selectListByUhostId(DeviceInfo device);

    void updateByNodeId(List<DeviceInfo> devices);


    /**
     * 添加一条device
     *
     * @param device
     */
    void add(DeviceInfo device);


    /**
     * 删除一条device
     *
     * @param id
     */
    void delete(Long id);

    Long count(DeviceInfo device);

    /**
     * 编辑device
     *
     * @param device
     */
    void edit(DeviceInfo device);

    /**
     * 查询device列表
     *
     * @param device
     * @param pageable
     * @return
     */
    List<DeviceInfo> findList(DeviceInfo device, Pageable pageable);

    List<FlowList> findByIds(List<String> deviceIds);

    void addProcessDetail(DeviceProcessDetail detail);

    List<DeviceProcessDetail> findByDetail(DeviceProcessDetail detail);

    List<DeviceProcessDetail> findDetailByPid(FlowList flowList, Pageable pageable);

    long countDetail(FlowList flowList);

    void setDeviceState(DeviceInfo deviceInfo, Integer deviceNotOnline, String token);

    DeviceInfo selectListByEntity(DeviceInfo deviceInfo);

    List<DeviceProcessDetail> findFailList(DeviceProcessDetail deviceProcessDetail, Pageable pageable);

    DeviceInfo getById(Long deviceId);

    List<DeviceInfo> selectListByToken(String token);

    void updateUhost(DeviceInfo deviceInfo);
}
