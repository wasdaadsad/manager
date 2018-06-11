package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ModelInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/4
 */
public interface IModelInfoDao {

    List<ModelInfo> selectAll();

    void insert(ModelInfo modelInfo);

    void update(ModelInfo ModelInfo);

    void delete(Integer id);

    //搜索展示列表实现OK
    List<ModelInfo> queryList(ModelInfo modelInfo, Pageable pageable);

    //数据统计OK
    Long count(ModelInfo modelInfo);

    List<ModelInfo> getModelByNameAndVersion(ModelInfo modelInfo, Pageable pageable);

    ModelInfo getModelById(Integer id);

}
