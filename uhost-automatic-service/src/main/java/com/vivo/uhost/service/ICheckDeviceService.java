package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.CheckDevice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICheckDeviceService {
    /**
     * 查询流程
     *
     * @return
     */
    List<CheckDevice> findAll();



    /**
     * 添加流程
     *
     * @param device
     */
    void add(CheckDevice device);


    /**
     * 删除流程
     *
     * @param id
     */
    void delete(Long id);
    Long count(CheckDevice device);


    /**
     * 编辑流程
     *
     * @param device
     */
    void edit(CheckDevice device);



    /**
     * 查询流程列表
     *
     * @param device
     * @param pageable
     *
     * @return
     */
    List<CheckDevice> findList(CheckDevice device, Pageable pageable);
}
