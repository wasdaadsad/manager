package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ToolInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author litingfa  2018/3/26 11:46
 * @version 1.0
 * @description
 */
public interface IToolInfoDao {
    long count(ToolInfo toolInfo);

    List<ToolInfo> findList(ToolInfo toolInfo, Pageable pageable);

    void changeState(ToolInfo toolInfo);

    ToolInfo findById(ToolInfo toolInfo);

    long addTool(ToolInfo toolInfo);

    ToolInfo findByName(String toolName);

    void update(ToolInfo toolInfo);

    List<ToolInfo> getAllToolList(Integer toolStateValid);
}
