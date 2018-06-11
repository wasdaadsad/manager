/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.service;

import com.vivo.uhost.core.domain.bo.FlowListBO;
import com.vivo.uhost.dal.entity.FlowList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlowListService {

    /**
     * 查询流程
     *
     * @return
     */
    List<FlowList> findAll();



    /**
     * 添加流程
     *
     * @param flowList
     */
    void add(FlowList flowList);


    /**
     * 删除流程
     *
     * @param id
     */
    void delete(Long id);
    Long count(FlowListBO flowList);


    /**
     * 编辑流程
     *
     * @param flowList
     */
    void edit(FlowList flowList);



    /**
     * 查询流程列表
     *
     * @param flowList
     * @param pageable
     *
     * @return
     */
    List<FlowList> findList(FlowListBO flowList, Pageable pageable);

    List<FlowList> findByIds(List<String> processIds);

    FlowList findById(String processId);

    void updateState(String s, Integer deviceProcessDetailRunning);

    void setCount(int count, String processId);
}
