/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.FlowList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlowListDao {


    List<FlowList> selectAll();

    void insert(FlowList flowList);

    void delete(Long id);

    void update(FlowList flowList);

    List<FlowList> queryList(FlowList flowList, Pageable pageable);

    Long count(FlowList flowList);

    List<FlowList> findByIds(List<String> processIds);

    FlowList findById(String processId);

    void updateState(String processId, Integer state);

    void setCount(int count, String processId);
}
