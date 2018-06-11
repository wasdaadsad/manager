package com.vivo.uhost.service;

import com.vivo.uhost.core.domain.bo.ToolVersionBo;
import com.vivo.uhost.dal.entity.ToolVersion;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author litingfa  2018/3/26 20:23
 * @version 1.0
 * @description
 */
public interface IToolVersionService {
    List<ToolVersionBo> findList(ToolVersion toolVersion, Pageable pageable);

    long count(ToolVersion toolVersion);

    void addToolVersion(ToolVersion toolVersion);

    void changeVersionState(ToolVersion toolVersion);

    List<ToolVersionBo> getVersionByState(Integer toolVersionValid);

    List<ToolVersion> getVersionList(ToolVersion toolVersion);

    ToolVersion findVersion(Long toolId, String testVersion);
}
