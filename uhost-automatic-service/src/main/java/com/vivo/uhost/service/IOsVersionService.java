package com.vivo.uhost.service;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.OsVersionBO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
public interface IOsVersionService {
    /**
     * 查询系统版本列表
     *
     * @return
     */
    List<OsVersionBO> findAll();


    List<OsVersionBO> selectAllDistinctModelId();

    /**
     * 添加一条版本信息
     *
     * @param osVersionBO
     */
    void insertOsVersion(OsVersionBO osVersionBO);

    /**
     *更新一条版本信息
     *
     * @param osVersionBO
     */
    void updateOsVersion(OsVersionBO osVersionBO);

    /**
     *删除一条版本信息
     *
     * @param id
     */
    void deleteOsVersion(Integer id);

    /**
     * 根据版本编号搜索
     *
     * @param versionCode
     */
    OsVersionBO getByVersionCode(String versionCode);

    /**
     * 根据id搜索
     *
     * @param id
     */
    OsVersionBO getOsVersionById(Integer id);

    /**
     * 查询版本列表
     *
     * @param osVersionBO
     * @param pageable
     * @return
     */
    SimplePage<OsVersionBO> getOsVersionList(OsVersionBO osVersionBO, Pageable pageable);

    /**
     * 查询版本列表
     * @param state = 1
     * @param osVersionBO
     * @param pageable
     * @return
     */
    SimplePage<OsVersionBO> queryOsVersionByState(Integer state, OsVersionBO osVersionBO, Pageable pageable);

    List<OsVersionBO> getVersions(OsVersionBO osVersionBO);
}
