package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.ProcessDetail;

import java.util.List;

/**
 * @author litingfa  2018/3/23 15:28
 * @version 1.0
 * @description
 */
public interface IProcessDetailService {
    List<ProcessDetail> findByPid(String processId);
}
