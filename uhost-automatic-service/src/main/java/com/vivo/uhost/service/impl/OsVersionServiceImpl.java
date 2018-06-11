package com.vivo.uhost.service.impl;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.OsVersionBO;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IModelInfoDao;
import com.vivo.uhost.dal.dao.IOsVersionDao;
import com.vivo.uhost.dal.entity.ModelInfo;
import com.vivo.uhost.dal.entity.OsVersion;
import com.vivo.uhost.service.IOsVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
@Service("osVersionService")
public class OsVersionServiceImpl implements IOsVersionService{

    @Autowired
    private IOsVersionDao osVersionDao;

    @Autowired
    private IModelInfoDao modelInfoDao;

    @Override
    public List<OsVersionBO> findAll() {
        List<OsVersionBO> list = new ArrayList<>();
        List<OsVersion> osVersions = osVersionDao.selectAll();
        for (OsVersion osVersion : osVersions) {
            list.add(new OsVersionBO(osVersion));
        }
        return list;
    }


    @Override
    public List<OsVersionBO> selectAllDistinctModelId() {
        List<OsVersionBO> list = new ArrayList<>();
        List<OsVersion> osVersions = osVersionDao.selectAllDistinctModelId();
        for (OsVersion osVersion : osVersions) {
            list.add(new OsVersionBO(osVersion));
        }
        return list;
    }




    @Override
    public void insertOsVersion(OsVersionBO osVersionBO) {
        osVersionDao.insert(osVersionBO.toOsVersion());
    }

    @Override
    public void updateOsVersion(OsVersionBO osVersionBO) {
        osVersionDao.update(osVersionBO.toOsVersion());

    }

    @Override
    public void deleteOsVersion(Integer id) {
        osVersionDao.delete(id);
    }

    @Override
    public OsVersionBO getByVersionCode(String versionCode) {
        OsVersionBO osVersionBO = new OsVersionBO();
        OsVersion osVersion = osVersionDao.getByVersionCode(versionCode);
        osVersionBO.setId(osVersion.getId());
        osVersionBO.setOsType(osVersion.getOsType());
        osVersionBO.setModelId(osVersion.getModelId());
        osVersionBO.setVersionCode(osVersion.getVersionCode());
        osVersionBO.setCreateTime(osVersion.getCreateTime());
        osVersionBO.setPackageUrl(osVersion.getPackageUrl());
        osVersionBO.setState(osVersion.getState());
        return osVersionBO;
    }

    @Override
    public OsVersionBO getOsVersionById(Integer id) {

        OsVersionBO osVersionBO = new OsVersionBO();
        OsVersion osVersion = osVersionDao.getOsVersionById(id);
        osVersionBO.setId(osVersion.getId());
        osVersionBO.setOsType(osVersion.getOsType());
        osVersionBO.setModelId(osVersion.getModelId());
        osVersionBO.setVersionCode(osVersion.getVersionCode());
        osVersionBO.setCreateTime(osVersion.getCreateTime());
        osVersionBO.setPackageUrl(osVersion.getPackageUrl());
        osVersionBO.setState(osVersion.getState());
        return osVersionBO;
    }

    @Override
    public SimplePage<OsVersionBO> getOsVersionList(OsVersionBO osVersionBO, Pageable pageable) {
        OsVersion osVersion = osVersionBO.toOsVersion();
        osVersion.setId(osVersionBO.getId());
        osVersion.setOsType(osVersionBO.getOsType());
        osVersion.setModelId(osVersionBO.getModelId());
        osVersion.setVersionCode(osVersionBO.getVersionCode());
        osVersion.setPackageUrl(osVersionBO.getPackageUrl());
        osVersion.setCreateTime(osVersionBO.getCreateTime());
        osVersion.setState(osVersionBO.getState());

        List<OsVersion> osVersions = osVersionDao.queryList(osVersion, pageable);

        List<OsVersionBO> list = new ArrayList<>();
        for (OsVersion version : osVersions){
            ModelInfo modelById = modelInfoDao.getModelById(version.getModelId());
            OsVersionBO res = new OsVersionBO(version);
            res.setModelName(modelById.getModelName());
            list.add(res);
        }
        Long count = osVersionDao.count(osVersion);
        return new SimplePage<>(list, pageable, count);
    }

    @Override
    public SimplePage<OsVersionBO> queryOsVersionByState(Integer state, OsVersionBO osVersionBO, Pageable pageable) {

        OsVersion osVersion = osVersionBO.toOsVersion();
        osVersion.setId(osVersionBO.getId());
        osVersion.setOsType(osVersionBO.getOsType());
        osVersion.setModelId(osVersionBO.getModelId());
        osVersion.setVersionCode(osVersionBO.getVersionCode());
        osVersion.setPackageUrl(osVersionBO.getPackageUrl());
        osVersion.setCreateTime(osVersionBO.getCreateTime());
        osVersion.setState(state);
        List<OsVersion> osVersions = osVersionDao.queryList(osVersion, pageable);
        List<OsVersionBO> list = new ArrayList<>();
        List<String> versionList = new ArrayList<>();
        for (OsVersion version : osVersions){
            list.add(new OsVersionBO(version));
            versionList.add(version.getVersionCode());
        }
        Long count = osVersionDao.count(osVersion);
        return new SimplePage<>(list, pageable, count);
    }

    @Override
    public List<OsVersionBO> getVersions(OsVersionBO osVersionBO) {
        OsVersion osVersion = osVersionBO.toOsVersion();

        osVersion.setModelId(osVersionBO.getModelId());
        osVersion.setState(CfgContants.STATE_VALID);
        List<OsVersion> osVersions = osVersionDao.getVersions(osVersion);
        List<OsVersionBO> list = new ArrayList<>();
        List<String> versionList = new ArrayList<>();
        for (OsVersion version : osVersions){
            list.add(new OsVersionBO(version));
            versionList.add(version.getVersionCode());
        }
        return list;
    }

}
