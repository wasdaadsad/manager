package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IModelInfoDao;
import com.vivo.uhost.dal.entity.ModelInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/4
 */
@Repository
public class ModelInfoDao implements IModelInfoDao{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ModelInfo> selectAll() {
        return sqlSession.selectList("ModelInfo.selectAll");
    }

    @Override
    public void insert(ModelInfo modelInfo) {
        //String time = DateUtil.formatNowTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(Calendar.getInstance().getTime());
        modelInfo.setCreateTime(time);
        sqlSession.insert("ModelInfo.insert", modelInfo);
    }

    @Override
    public void update(ModelInfo modelInfo){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(Calendar.getInstance().getTime());
        modelInfo.setCreateTime(time);
        //Date date = new Date(); Sat Jan 06 17:27:27 CST 2018
        sqlSession.update("ModelInfo.update",modelInfo);
    }

    @Override
    public void delete(Integer id) {
        sqlSession.delete("ModelInfo.delete", id);
    }

    @Override
    public List<ModelInfo> queryList(ModelInfo modelInfo, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        map.put("modelInfo", modelInfo);
        map.put("offset", pageable.getOffset());
        map.put("pageSize", pageable.getPageSize());
        //System.out.println("queryList:"+modelInfo);
        return sqlSession.selectList("ModelInfo.selectList", map);
    }

    @Override
    public Long count(ModelInfo modelInfo) {
        return sqlSession.selectOne("ModelInfo.count", modelInfo);
    }

    @Override
    public List<ModelInfo> getModelByNameAndVersion(ModelInfo modelInfo, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        map.put("modelInfo", modelInfo);
        map.put("offset", pageable.getOffset());
        map.put("pageSize", pageable.getPageSize());
        return sqlSession.selectList("ModelInfo.selectByNameAndVersion",map);
    }

    @Override
    public ModelInfo getModelById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("modelId", id);
        return sqlSession.selectOne("ModelInfo.getById", map);
    }
}
