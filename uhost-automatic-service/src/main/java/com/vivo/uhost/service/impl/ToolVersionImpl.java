package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.domain.bo.ToolVersionBo;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IToolVersionDao;
import com.vivo.uhost.dal.entity.ToolVersion;
import com.vivo.uhost.service.IToolVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litingfa  2018/3/26 20:23
 * @version 1.0
 * @description
 */
@Service
public class ToolVersionImpl implements IToolVersionService {

    @Autowired
    private IToolVersionDao toolVersionDao;

    @Override
    public List<ToolVersionBo> findList(ToolVersion toolVersion, Pageable pageable) {
        List<ToolVersion> toolVersions = toolVersionDao.findList(toolVersion, pageable);
        List<ToolVersionBo> toolVersionBos = toBOForEntity(toolVersions);
        return toolVersionBos;
    }

    /**
     * 转换成BO
     * @param toolVersions
     * @return
     */
    private List<ToolVersionBo> toBOForEntity(List<ToolVersion> toolVersions) {
        List<ToolVersionBo> toolVersionBos = new ArrayList<>();
        for (ToolVersion toolVersion : toolVersions) {
            ToolVersionBo toolVersionBo = new ToolVersionBo(toolVersion);
            toolVersionBos.add(toolVersionBo);
        }
        return toolVersionBos;
    }

    @Override
    public long count(ToolVersion toolVersion) {
        return toolVersionDao.count(toolVersion);
    }

    @Override
    public void addToolVersion(ToolVersion toolVersion) {
        toolVersion.setCreateTime(new Date());
        toolVersion.setState(CfgContants.TOOL_VERSION_VALID);
        toolVersionDao.addToolVersion(toolVersion);
    }

    @Override
    public void changeVersionState(ToolVersion toolVersion) {
        toolVersionDao.changeVersionState(toolVersion);
    }

    @Override
    public List<ToolVersionBo> getVersionByState(Integer toolVersionValid) {
        List<ToolVersion> toolVersions = toolVersionDao.getByState(toolVersionValid);
        List<ToolVersionBo> toolVersionBos = new ArrayList<>();
        for (ToolVersion toolVersion : toolVersions) {
            ToolVersionBo toolVersionBo = new ToolVersionBo(toolVersion);
            toolVersionBos.add(toolVersionBo);
        }
        return toolVersionBos;
    }

    @Override
    public List<ToolVersion> getVersionList(ToolVersion toolVersion) {
        return toolVersionDao.getVersionList(toolVersion);
    }

    @Override
    public ToolVersion findVersion(Long toolId, String testVersion) {
        ToolVersion toolVersion = new ToolVersion();
        toolVersion.setToolId(Integer.valueOf(toolId.toString()));
        toolVersion.setVersionName(testVersion);
        return toolVersionDao.findVersion(toolVersion);
    }
}
