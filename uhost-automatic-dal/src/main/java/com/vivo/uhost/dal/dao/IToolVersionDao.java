package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ToolVersion;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author litingfa  2018/3/26 20:24
 * @version 1.0
 * @description
 */
public interface IToolVersionDao {
    List<ToolVersion> findList(ToolVersion toolVersion, Pageable pageable);

    long count(ToolVersion toolVersion);

    void addToolVersion(ToolVersion toolVersion);

    void changeVersionState(ToolVersion toolVersion);

    List<ToolVersion> getByState(Integer toolVersionValid);

    List<ToolVersion> getVersionList(ToolVersion toolVersion);

    ToolVersion findById(Long aLong);

    ToolVersion findVersion(ToolVersion toolVersion);
}
