package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IDeviceTaskDao;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/4/24 19:52
 * @version 1.0
 * @description
 */
@Repository
public class DeviceTaskDaoImpl implements IDeviceTaskDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void changeTaskState(DeviceProcessDetail deviceProcessDetail) {
        Map<String, Object> params = new HashMap<>();
        params.put("detailId", deviceProcessDetail.getDetailId());
        params.put("deviceId", deviceProcessDetail.getDeviceId());
        params.put("state", deviceProcessDetail.getStatus());
        sqlSession.update("DeviceProcessDetailMapper.changeTaskState", params);
    }

    @Override
    public List<DeviceProcessDetail> findByRun(Integer msgId, Integer deviceStatusRun) {
        Map<String, Object> params = new HashMap<>();
        params.put("detailId", msgId);
        params.put("state", deviceStatusRun);
        return sqlSession.selectList("DeviceProcessDetailMapper.findByRun", params);
    }
}
