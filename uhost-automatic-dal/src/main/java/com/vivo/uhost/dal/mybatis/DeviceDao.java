package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IDeviceDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.FlowList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaojun on 2018/1/3.
 */
@Repository
public class DeviceDao implements IDeviceDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<DeviceInfo> selectAll() {
        return sqlSession.selectList("DeviceMapper.selectAll");
    }

    @Override
    public void insert(DeviceInfo device) {
        sqlSession.insert("DeviceMapper.insert", device);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("DeviceMapper.delete", id);
    }

    @Override
    public void update(DeviceInfo device) {
        sqlSession.update("DeviceMapper.update", device);
    }

    @Override
    public List<DeviceInfo> queryList(DeviceInfo device, Pageable pageable) {

        if (pageable != null) {
            device.setOffset(pageable.getOffset());
            device.setPageSize(pageable.getPageSize());
        }
        return sqlSession.selectList("DeviceMapper.selectList", device);
    }

    @Override
    public Long count(DeviceInfo device) {
        return sqlSession.selectOne("DeviceMapper.count", device);
    }

    @Override
    public List<DeviceInfo> selectListByUhostId(DeviceInfo device) {
        return sqlSession.selectList("DeviceMapper.selectListByUhostId", device);
    }

    @Override
    public List<DeviceInfo> selectListByToken(String token) {
        return sqlSession.selectList("DeviceMapper.selectListByToken", token);
    }

    @Override
    public void updateByNodeId(List<DeviceInfo> device) {
        sqlSession.update("DeviceMapper.updateByNodeId", device);
    }

    @Override
    public void addProcessDetail(DeviceProcessDetail detail) {
        sqlSession.insert("DeviceProcessDetailMapper.add", detail);
    }

    @Override
    public List<DeviceProcessDetail> findByDetail(DeviceProcessDetail detail) {
        return sqlSession.selectList("DeviceProcessDetailMapper.findByDetail", detail);
    }

    @Override
    public List<DeviceProcessDetail> findByProcess(FlowList flowList, Pageable pageable, Integer status) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("flowList", flowList);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("pageSize", pageable.getPageSize());
        }
        return sqlSession.selectList("DeviceProcessDetailMapper.findByProcess", params);
    }

    @Override
    public long countDetail(FlowList flowList) {
        return sqlSession.selectOne("DeviceProcessDetailMapper.countDetail", flowList);
    }

    @Override
    public DeviceInfo selectListByEntity(DeviceInfo deviceInfo) {
        return sqlSession.selectOne("DeviceMapper.selectByEntity", deviceInfo);
    }

    @Override
    public void updateState(DeviceInfo deviceInfo) {
        sqlSession.update("DeviceMapper.updateState", deviceInfo);
    }

    @Override
    public List<DeviceProcessDetail> findFailList(DeviceProcessDetail deviceProcessDetail, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("deviceProcessDetail", deviceProcessDetail);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("pageSize", pageable.getPageSize());
        }
        return sqlSession.selectList("DeviceProcessDetailMapper.findFailList", params);
    }

    @Override
    public DeviceInfo getById(Long deviceId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", deviceId);
        return sqlSession.selectOne("DeviceMapper.getById", params);
    }

    @Override
    public String getMedelVersionById(Long aLong) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", aLong);
        return sqlSession.selectOne("DeviceMapper.getMedelVersionById", params);
    }

    @Override
    public DeviceInfo findByEmecid(String emmcId) {
        Map<String, Object> params = new HashMap<>();
        params.put("emmcId", emmcId);
        return sqlSession.selectOne("DeviceMapper.findByEmecid",params);
    }

    @Override
    public void updateUhost(DeviceInfo deviceInfo) {
        sqlSession.update("DeviceMapper.updateUhost", deviceInfo);
    }
}
