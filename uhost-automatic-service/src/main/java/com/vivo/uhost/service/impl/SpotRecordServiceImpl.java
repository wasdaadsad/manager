package com.vivo.uhost.service.impl;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.SpotRecordBO;
import com.vivo.uhost.service.ISpotRecordService;
import com.vivo.uhost.dal.entity.SpotRecord;
import com.vivo.uhost.dal.mybatis.SpotRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
@Service("spotRecordService")
public class SpotRecordServiceImpl implements ISpotRecordService{

    @Autowired
    private SpotRecordDao spotRecordDao;

    @Override
    public List<SpotRecordBO> findAll() {
        List<SpotRecordBO> list = new ArrayList<>();
        List<SpotRecord> spotRecords = spotRecordDao.selectAll();
        for (SpotRecord spotRecord : spotRecords){
            list.add(new SpotRecordBO(spotRecord));
        }
        return list;
    }

    @Override
    public void insertSpotRecord(SpotRecordBO spotRecordBO) {
        spotRecordDao.insert(spotRecordBO.toSpotRecord());
    }

    @Override
    public void updateSpotRecord(SpotRecordBO spotRecordBO) {
        spotRecordDao.update(spotRecordBO.toSpotRecord());
    }

    @Override
    public void deleteSpotRecord(Integer id) {
        spotRecordDao.delete(id);
    }

    @Override
    public SimplePage<SpotRecordBO> getSpotRecordList(SpotRecordBO spotRecordBO, Pageable pageable) {
        SpotRecord spotRecord = spotRecordBO.toSpotRecord();

        spotRecord.setId(spotRecordBO.getId());
        spotRecord.setTestNum(spotRecordBO.getTestNum());
        spotRecord.setRecordIp(spotRecordBO.getRecordIp());
        spotRecord.setModelVersion(spotRecordBO.getModelVersion());
        spotRecord.setSoftVersion(spotRecordBO.getSoftVersion());
        spotRecord.setUpdateTime(spotRecordBO.getUpdateTime());
        spotRecord.setRecordUrl(spotRecordBO.getRecordUrl());
        spotRecord.setRecordComment(spotRecordBO.getRecordComment());

        List<SpotRecord> spotRecords = spotRecordDao.queryList(spotRecord, pageable);
        List<SpotRecordBO> list = new ArrayList<>();
        for (SpotRecord record : spotRecords){
            list.add(new SpotRecordBO(record));
        }
        Long count = spotRecordDao.count(spotRecord);
        return new SimplePage<>(list, pageable, count);
    }

    public SpotRecordBO getBySpotRecordId(Integer id) {
        SpotRecordBO spotRecordBO = new SpotRecordBO();
        SpotRecord spotRecord = spotRecordDao.getBySpotRecordId(id);

        spotRecordBO.setId(spotRecord.getId());
        spotRecordBO.setTestNum(spotRecord.getTestNum());
        spotRecordBO.setRecordIp(spotRecord.getRecordIp());
        spotRecordBO.setModelVersion(spotRecord.getModelVersion());
        spotRecordBO.setSoftVersion(spotRecord.getSoftVersion());
        spotRecordBO.setUpdateTime(spotRecord.getUpdateTime());
        spotRecordBO.setRecordUrl(spotRecord.getRecordUrl());
        spotRecordBO.setRecordComment(spotRecord.getRecordComment());
        return spotRecordBO;
    }

}
