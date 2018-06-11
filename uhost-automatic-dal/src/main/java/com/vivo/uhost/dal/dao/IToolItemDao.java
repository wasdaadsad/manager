package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ToolItem;
import com.vivo.uhost.dal.entity.process.TestItem;

import java.util.List;

/**
 * @author litingfa  2018/3/27 17:15
 * @version 1.0
 * @description
 */
public interface IToolItemDao {
    void insertTestItem(ToolItem testItem);

    void updateItem(ToolItem testItem);

    List<ToolItem> findByTid(Long toolId);

    void deleteById(Long id);

    List<ToolItem> getByTaskId(Integer taskId);
}
