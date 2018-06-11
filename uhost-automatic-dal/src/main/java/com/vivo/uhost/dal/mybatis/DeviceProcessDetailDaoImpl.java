package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IDeviceProcessDetailDao;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.ProcessDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/4/8 14:50
 * @version 1.0
 * @description
 */
@Repository
public class DeviceProcessDetailDaoImpl implements IDeviceProcessDetailDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<DeviceProcessDetail> findByPid(String pId) {
        Map<String, Object> params = new HashMap<>();
        params.put("detailId", 0);
        params.put("processId", pId);
        return sqlSession.selectList("DeviceProcessDetailMapper.findByPid",params);
    }
}
