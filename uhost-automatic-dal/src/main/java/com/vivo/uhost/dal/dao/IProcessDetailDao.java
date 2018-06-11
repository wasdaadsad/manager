package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ProcessDetail;

import java.util.List;

/**
 * @author litingfa  2018/3/23 16:09
 * @version 1.0
 * @description
 */
public interface IProcessDetailDao {
    List<ProcessDetail> findByPid(String processId);
}
