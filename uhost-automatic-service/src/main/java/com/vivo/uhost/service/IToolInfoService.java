package com.vivo.uhost.service;

import com.vivo.uhost.core.domain.bo.ToolInfoBO;
import com.vivo.uhost.dal.entity.ToolInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author litingfa  2018/3/26 11:45
 * @version 1.0
 * @description
 */
public interface IToolInfoService {
    List<ToolInfoBO> findList(ToolInfo toolInfo, Pageable pageable);

    long count(ToolInfo toolInfo);

    void changeState(ToolInfo toolInfo);

    ToolInfoBO findById(ToolInfo toolInfo);

    ToolInfo addTool(ToolInfo toolInfo);

    ToolInfo findByName(String toolName);

    void updateTool(ToolInfo toolInfo);

    List<ToolInfo> getAllToolList(Integer toolStateValid);
}
