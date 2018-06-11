package com.vivo.uhost.service;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.dal.entity.ModelInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
public interface IModelInfoService {

    /**
     * 查询机型
     *
     * @return
     */
    List<ModelInfoBO> findAll();

    /**
     * 添加一条model机型信息
     *
     * @param modelInfoBO
     */
    void insertModelInfo(ModelInfoBO modelInfoBO);

    /**
     *更新一条model机型信息
     *
     * @param modelInfoBO
     */
    void updateModelInfo(ModelInfoBO modelInfoBO);

    /**
     *删除一条model机型信息
     *
     * @param id
     */
    void deleteModelInfo(Integer id);

    /**
     * 根据机型版本搜索
     *
     * @param modelVersion
     */
    void getModelByVersion(String modelVersion);

    /**
     * 根据id搜索
     *
     * @param id
     */
    ModelInfo getModelById(Integer id);

    /**
     * 查询机型列表
     *
     * @param modelInfoBO
     * @param pageable
     * @return
     */
    SimplePage<ModelInfoBO> getModelList(ModelInfoBO modelInfoBO, Pageable pageable);

    /**
     * 条件查询机型列表
     *
     * @param modelInfoBO
     * @param pageable
     * @return
     */
    SimplePage<ModelInfoBO> getModelSearchList(ModelInfoBO modelInfoBO, Pageable pageable);

    /**
     * 条件查询机型列表
     * @param state =1
     * @param modelInfoBO
     * @param pageable
     * @return
     */
    SimplePage<ModelInfoBO> getModelStateList(Integer state, ModelInfoBO modelInfoBO, Pageable pageable);
}