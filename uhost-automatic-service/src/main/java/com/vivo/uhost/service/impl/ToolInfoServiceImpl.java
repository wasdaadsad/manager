package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.domain.bo.ToolInfoBO;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IToolInfoDao;
import com.vivo.uhost.dal.entity.ToolInfo;
import com.vivo.uhost.service.IToolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litingfa  2018/3/26 11:45
 * @version 1.0
 * @description
 */
@Service
public class ToolInfoServiceImpl implements IToolInfoService {

    @Autowired
    private IToolInfoDao toolInfoDao;

    @Override
    public List<ToolInfoBO> findList(ToolInfo toolInfo, Pageable pageable) {
        List<ToolInfo> toolInfos = toolInfoDao.findList(toolInfo,pageable);
        List<ToolInfoBO> toolInfoBOS = toBOForToolInfos(toolInfos);
        return toolInfoBOS;
    }

    /**
     * tool List 转换
     * @param toolInfos
     * @return
     */
    private List<ToolInfoBO> toBOForToolInfos(List<ToolInfo> toolInfos) {
        List<ToolInfoBO> toolInfoBOS = new ArrayList<>();
        for (ToolInfo toolInfo : toolInfos) {
            ToolInfoBO toolInfoBO = new ToolInfoBO(toolInfo);
            toolInfoBOS.add(toolInfoBO);
        }
        return toolInfoBOS;
    }

    @Override
    public long count(ToolInfo toolInfo) {
        return toolInfoDao.count(toolInfo);
    }

    @Override
    public void changeState(ToolInfo toolInfo) {
        toolInfoDao.changeState(toolInfo);
    }

    @Override
    public ToolInfoBO findById(ToolInfo toolInfo) {
        ToolInfo res = toolInfoDao.findById(toolInfo);
        ToolInfoBO toolInfoBO = new ToolInfoBO(res);
        return toolInfoBO;
    }

    @Override
    public ToolInfo addTool(ToolInfo toolInfo) {
        toolInfo.setCreateTime(new Date());
        toolInfo.setState(CfgContants.TOOL_STATE_VALID);
        toolInfoDao.addTool(toolInfo);
        return toolInfo;
    }

    @Override
    public ToolInfo findByName(String toolName) {
        return toolInfoDao.findByName(toolName);
    }

    @Override
    public void updateTool(ToolInfo toolInfo) {
        toolInfoDao.update(toolInfo);
    }

    @Override
    public List<ToolInfo> getAllToolList(Integer toolStateValid) {
        return toolInfoDao.getAllToolList(toolStateValid);
    }
}
