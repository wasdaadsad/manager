package com.vivo.uhost.web.controller;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.dal.entity.ModelInfo;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.ModelInfoServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
@Controller
public class ModelInfoController extends BaseController {

    private static Log log = LogFactory.getLog(ModelInfoController.class);

    @Autowired
    private ModelInfoServiceImpl modelInfoServiceImpl;

    @RequestMapping("uhostmanage/modelManage/home")
    public String home(ModelMap modelMap) {
        List<ModelInfoBO> res = modelInfoServiceImpl.findAll();

        Set<String> modelNameSet = new HashSet<>();
        Set<String> modelVersionSet = new HashSet<>();

        for (ModelInfoBO re : res) {
            modelNameSet.add(re.getModelName());
            modelVersionSet.add(re.getModelVersion());
        }

        List<String> modelNames = new ArrayList<>();
        List<String> modelVersions = new ArrayList<>();

        modelNames.addAll(modelNameSet);
        modelVersions.addAll(modelVersionSet);

        modelMap.addAttribute("modelNames",modelNames);
        modelMap.addAttribute("modelVersions",modelVersions);

        return "modelmanage/home";
    }

    @RequestMapping("uhostmanage/modelManage/add")
    public void addModel(ModelInfoBO modelInfoBO) {
        log.debug("进入add");
        modelInfoServiceImpl.insertModelInfo(modelInfoBO);
    }

    @RequestMapping("uhostmanage/modelManage/update")
    public void updateModel(ModelInfoBO modelInfoBO) {
        log.debug("进入update");
        modelInfoServiceImpl.updateModelInfo(modelInfoBO);
    }

    @RequestMapping("uhostmanage/modelManage/delete")
    public void deleteModel(@Param("id") Integer id) {
        log.debug("进入delete");
        modelInfoServiceImpl.deleteModelInfo(id);
    }

    @RequestMapping("uhostmanage/modelManage/seachList")
    public BaseJsonResponse getModelByNameAndVersion(ModelInfoBO modelInfoBO, Pageable pageable) {
        log.debug("进入seachList");
        SimplePage<ModelInfoBO> simplePage = modelInfoServiceImpl.getModelSearchList(modelInfoBO, pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    @RequestMapping("uhostmanage/modelManage/id")
    public void getModelById(@Param("id") Integer id) {
        log.debug("进入id");
        modelInfoServiceImpl.getModelById(id);
    }

    @RequestMapping("uhostmanage/modelManage/list")
    @ResponseBody
    public BaseJsonResponse getModelList(ModelInfoBO modelInfoBO, Pageable pageable) {
        log.debug("进入list");
        SimplePage<ModelInfoBO> simplePage = modelInfoServiceImpl.getModelList(modelInfoBO, pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    @RequestMapping("uhostmanage/modelManage/statelist")
    @ResponseBody
    public BaseJsonResponse getModelStateList(@Param("state") Integer state, ModelInfoBO modelInfoBO, Pageable pageable) {
        log.debug("进入statelist");
        SimplePage<ModelInfoBO> simplePage = modelInfoServiceImpl.getModelStateList(state, modelInfoBO, pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*弹出编辑机型模态框*/
    @RequestMapping("uhostmanage/modelManage/editModel")
    public ModelAndView selectItem(Integer id,String modelVersion,String modelName,Integer state) {
        ModelAndView modelAndView = new ModelAndView("modelmanage/editModel");
        modelAndView.addObject("id", id);
        try {
            String param1 = new String(modelVersion.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("modelVersion", param1);
            String param2 = new String(modelName.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("modelName", param2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("state", state);
        return modelAndView;
    }


    /*弹出添加机型模态框*/
    @RequestMapping("uhostmanage/modelManage/addModel")
    public String selectItem2() {
        return "modelmanage/addModel";
    }
}
