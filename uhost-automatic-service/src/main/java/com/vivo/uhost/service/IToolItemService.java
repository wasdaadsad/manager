package com.vivo.uhost.service;

import com.vivo.uhost.core.domain.bo.ToolItemBO;
import com.vivo.uhost.dal.entity.process.TestItem;

import java.util.List; /**
 * @author litingfa  2018/3/27 17:14
 * @version 1.0
 * @description
 */
public interface IToolItemService {
    void saveTestItem4Tool(List<ToolItemBO> toolItemBOs, Long id);

    List<ToolItemBO> findByTid(Long id);

    List<TestItem> getListByToolId(Integer toolId);

    List<TestItem> getListByTaskId(Integer taskId);
}
