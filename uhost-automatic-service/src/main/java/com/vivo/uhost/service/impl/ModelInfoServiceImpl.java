package com.vivo.uhost.service.impl;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.service.IModelInfoService;
import com.vivo.uhost.dal.dao.IModelInfoDao;
import com.vivo.uhost.dal.entity.ModelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
@Service("modelInfoService")
public class ModelInfoServiceImpl implements IModelInfoService {

    @Autowired
    private IModelInfoDao modelInfoDao;

    @Override
    public List<ModelInfoBO> findAll() {
        List<ModelInfoBO> list = new ArrayList<>();

        List<ModelInfo> modelInfos = modelInfoDao.selectAll();
        for (ModelInfo modelInfo : modelInfos) {
            list.add(new ModelInfoBO(modelInfo));
        }
        return list;
    }

    @Override
    public void insertModelInfo(ModelInfoBO modelInfoBO) {
        ModelInfo modelInfo = modelInfoBO.toModelInfo();
        modelInfoDao.insert(modelInfo);
    }

    @Override
    public void updateModelInfo(ModelInfoBO modelInfoBO) {
        ModelInfo modelInfo = modelInfoBO.toModelInfo();
        modelInfoDao.update(modelInfo);
    }

    @Override
    public void deleteModelInfo(Integer id) {
        modelInfoDao.delete(id);
    }

    @Override
    public void getModelByVersion(String modelVersion) {

    }

    @Override
    public ModelInfo getModelById(Integer id) {
       return modelInfoDao.getModelById(id);
    }

    @Override
    public SimplePage<ModelInfoBO> getModelList(ModelInfoBO modelInfoBO, Pageable pageable) {

        ModelInfo modelInfo = modelInfoBO.toModelInfo();
        modelInfo.setId(modelInfoBO.getId());
        modelInfo.setModelVersion(modelInfoBO.getModelVersion());
        modelInfo.setState(modelInfoBO.getState());
        modelInfo.setModelName(modelInfoBO.getModelName());

        List<ModelInfo> modelInfos = modelInfoDao.queryList(modelInfo, pageable);
        List<ModelInfoBO> list = new ArrayList<>();
        for (ModelInfo model : modelInfos) {
            list.add(new ModelInfoBO(model));
        }
        Long count = modelInfoDao.count(modelInfo);
        return new SimplePage<>(list, pageable, count);
    }

    @Override
    public SimplePage<ModelInfoBO> getModelSearchList(ModelInfoBO modelInfoBO, Pageable pageable) {
        ModelInfo modelInfo = modelInfoBO.toModelInfo();
        List<ModelInfo> modelInfos = modelInfoDao.queryList(modelInfo, pageable);
        List<ModelInfoBO> list = new ArrayList<>();
        for (ModelInfo model : modelInfos) {
            list.add(new ModelInfoBO(model));
        }
        Long count = modelInfoDao.count(modelInfo);

        return new SimplePage<>(list, pageable, count);
    }

    @Override
    public SimplePage<ModelInfoBO> getModelStateList(Integer state, ModelInfoBO modelInfoBO, Pageable pageable) {
        ModelInfo modelInfo = modelInfoBO.toModelInfo();
        modelInfo.setId(modelInfoBO.getId());
        modelInfo.setModelVersion(modelInfoBO.getModelVersion());
        modelInfo.setModelName(modelInfoBO.getModelName());
        modelInfo.setState(state);

        List<ModelInfo> modelInfos = modelInfoDao.queryList(modelInfo, pageable);
        List<ModelInfoBO> list = new ArrayList<>();

        for (ModelInfo model : modelInfos) {
            list.add(new ModelInfoBO(model));
        }
        Long count = modelInfoDao.count(modelInfo);
        return new SimplePage<>(list, pageable, count);
    }

}
