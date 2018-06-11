package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.OsVersion;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
public interface IOsVersionDao {

    List<OsVersion> selectAll();

    void insert(OsVersion osVersion);

    void update(OsVersion osVersion);

    void delete(Integer id);

    //搜索展示列表实现OK
    List<OsVersion> queryList(OsVersion osVersion, Pageable pageable);

    //数据统计OK
    Long count(OsVersion osVersion);

    OsVersion getOsVersionById(Integer id);

    OsVersion getByVersionCode(String versionCode);

    List<OsVersion> getVersions(OsVersion osVersion);

    List<OsVersion> selectAllDistinctModelId();
}
