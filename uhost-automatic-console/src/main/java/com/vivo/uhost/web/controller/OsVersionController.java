package com.vivo.uhost.web.controller;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.core.domain.bo.OsVersionBO;
import com.vivo.uhost.dal.entity.ModelInfo;
import com.vivo.uhost.dal.entity.OsVersion;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.ModelInfoServiceImpl;
import com.vivo.uhost.service.impl.OsVersionServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
@Controller
public class OsVersionController extends BaseController {

    private static Log log = LogFactory.getLog(OsVersionController.class);

    @Autowired
    private OsVersionServiceImpl osVersionService;

    @Autowired
    private ModelInfoServiceImpl modelInfoService;

    @RequestMapping("uhostmanage/versionManage/home")
    public String home(ModelMap modelMap){

        List<OsVersionBO> res = osVersionService.selectAllDistinctModelId();//去重查询，确保ModelId不相同
        Set<ModelInfo> modelSet = new HashSet<>();
        List<ModelInfo> models = new ArrayList<>();
        for (OsVersionBO re : res) {
           ModelInfo modelInfo = modelInfoService.getModelById(re.getModelId());
           modelSet.add(modelInfo);
        }
        models.addAll(modelSet);
        modelMap.addAttribute("models",models);
        return "versionmanage/home";
    }

    @RequestMapping("uhostmanage/versionManage/add")
    public void addOsVersion(OsVersionBO osVersionBO){
        log.debug("进入add");
        osVersionService.insertOsVersion(osVersionBO);
    }

    @RequestMapping("uhostmanage/versionManage/update")
    public void updateOsVersion(OsVersionBO osVersionBO){
        log.debug("进入update");
        osVersionService.updateOsVersion(osVersionBO);
    }

    @RequestMapping("uhostmanage/versionManage/delete")
    public void deleteOsVersion(@Param("id") Integer id){
        log.debug("进入delete");
        osVersionService.deleteOsVersion(id);
    }

    @RequestMapping("uhostmanage/versionManage/statelist")
    @ResponseBody
    public BaseJsonResponse queryOsVersionByState(@Param("state") Integer state, OsVersionBO osVersionBO, Pageable pageable) {
        log.debug("进入statelist");
        SimplePage<OsVersionBO> simplePage = osVersionService.queryOsVersionByState(state, osVersionBO, pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    @RequestMapping("uhostmanage/versionManage/list")
    @ResponseBody
    public BaseJsonResponse getOsVersionList(OsVersionBO osVersionBO, Pageable pageable) {
        log.debug("进入list");
        SimplePage<OsVersionBO> simplePage = osVersionService.getOsVersionList(osVersionBO,pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    @RequestMapping("uhostmanage/toolManage/sysVersion")
    @ResponseBody
    public BaseJsonResponse sysVersion(OsVersionBO osVersionBO) {
        log.debug("sysVersion");
        List<OsVersionBO> simplePage = osVersionService.getVersions(osVersionBO);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*弹出编辑版本模态框*/
    @RequestMapping("uhostmanage/versionManage/editVersion")
    public ModelAndView selectItem(Integer id,String versionCode,String modelId,Integer osType,String packageUrl,Integer state) {
        ModelAndView modelAndView = new ModelAndView("versionmanage/editVersion");
        modelAndView.addObject("id", id);

        modelAndView.addObject("osType", osType);
        modelAndView.addObject("state", state);
        try {
            String param0 = new String(modelId.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("modelId", param0);
            String param1 = new String(versionCode.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("versionCode", param1);
            String param2 = new String(packageUrl.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("packageUrl", param2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<ModelInfoBO> modelInfoList = modelInfoService.findAll();
        modelAndView.addObject("modelInfoList",modelInfoList);
//       ModelInfo currentModel = modelInfoService.getModelById(modelId);//这个查询还没写，但也不需要回显
        return modelAndView;
    }


    /*弹出添加版本模态框*/
    @RequestMapping("uhostmanage/versionManage/addVersion")
    public ModelAndView selectItem2() {
        ModelAndView modelAndView = new ModelAndView("versionmanage/addVersion");
        List<ModelInfoBO> modelInfoList = modelInfoService.findAll();
        modelAndView.addObject("modelInfoList",modelInfoList);
        return modelAndView;
    }
}