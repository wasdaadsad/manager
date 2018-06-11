/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/10
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */
package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.CheckDevice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICheckDeviceDao {
    List<CheckDevice> selectAll();

    void insert(CheckDevice device);

    void delete(Long id);

    void update(CheckDevice device);

    List<CheckDevice> queryList(CheckDevice device, Pageable pageable);

    Long count(CheckDevice device);
}
