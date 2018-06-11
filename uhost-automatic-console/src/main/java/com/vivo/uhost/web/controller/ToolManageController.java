package com.vivo.uhost.web.controller;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.ToolInfoBO;
import com.vivo.uhost.core.domain.bo.ToolItemBO;
import com.vivo.uhost.core.domain.bo.ToolVersionBo;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.ToolInfo;
import com.vivo.uhost.dal.entity.ToolVersion;
import com.vivo.uhost.dal.entity.process.TestItem;
import com.vivo.uhost.service.IToolInfoService;
import com.vivo.uhost.service.IToolItemService;
import com.vivo.uhost.service.IToolVersionService;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.ModelInfoServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
@Controller
public class ToolManageController extends BaseController {

    private static Log log = LogFactory.getLog(ToolManageController.class);

    @Autowired
    private ModelInfoServiceImpl modelInfoServiceImpl;

    @Autowired
    private IToolVersionService toolVersionService;

    @Autowired
    private IToolItemService toolItemService;

    @Autowired
    private IToolInfoService toolInfoService;

    @RequestMapping("uhostmanage/toolmanage/home")
    public String home() {
        return "toolmanage/home";
    }

    @RequestMapping("uhostmanage/toolManage/ToolVersion")
    public String addTool(ModelMap modelMap,ToolInfoBO toolInfoBO){
        addModeMap(modelMap);
        ToolInfoBO res = toolInfoService.findById(toolInfoBO.toEntity());
        modelMap.addAttribute("toolInfo",res);
        return "toolmanage/toolVersion";
    }

    @RequestMapping("uhostmanage/toolManage/addTool")
    public String addTool(ModelMap modelMap){
        addModeMap(modelMap);
        return "toolmanage/editTool";
    }

    @RequestMapping("uhostmanage/toolManage/addToolVersion")
    @ResponseBody
    public BaseJsonResponse addToolVersion(ToolVersionBo toolVersionBo){
        if(StringUtils.isBlank(toolVersionBo.getVersionName()) || StringUtils.isBlank(toolVersionBo.getToolUrl())){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        try {
            toolVersionService.addToolVersion(toolVersionBo.toEntity());
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
    }

    @RequestMapping("uhostmanage/toolManage/toolAdd")
    @ResponseBody
    public BaseJsonResponse toolAdd(ToolInfoBO toolInfoBO){
        if(validateToolInfo(toolInfoBO)){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        ToolInfo toolInfo = toolInfoService.addTool(toolInfoBO.toEntity());
        toolInfoBO.setId(toolInfo.getId());
        toolItemService.saveTestItem4Tool(toolInfoBO.getToolItemBOs(), toolInfoBO.getId());
        return new BaseJsonResponse(BaseJsonResponse.OK);
    }

    @RequestMapping("uhostmanage/toolManage/toolUpdate")
    @ResponseBody
    public BaseJsonResponse toolUpdate(ToolInfoBO toolInfoBO){
        if(validateToolInfo(toolInfoBO) || toolInfoBO.getId() == null){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        ToolInfo toolInfo = toolInfoService.findByName(toolInfoBO.getToolName());
        if(toolInfo != null && !(toolInfo.getId().equals(toolInfoBO.getId()))){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        try {
            toolInfoService.updateTool(toolInfoBO.toEntity());
            if(CollectionUtils.isNotEmpty(toolInfoBO.getToolItemBOs())){
                toolItemService.saveTestItem4Tool(toolInfoBO.getToolItemBOs(), toolInfoBO.getId());
            }
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
    }

    private boolean validateToolInfo(ToolInfoBO toolInfoBO){
        if(StringUtils.isBlank(toolInfoBO.getToolName()) || StringUtils.isBlank(toolInfoBO.getPackageName()) ||
                StringUtils.isBlank(toolInfoBO.getStartCmd()) || CollectionUtils.isEmpty(toolInfoBO.getToolItemBOs())){
            return true;
        }
        return false;
    }

    @RequestMapping("uhostmanage/toolManage/editTool")
    public String editTool(ToolInfoBO toolInfoBO, ModelMap modelMap){
        ToolInfoBO res = toolInfoService.findById(toolInfoBO.toEntity());
        List<ToolItemBO> toolItemBOS = toolItemService.findByTid(toolInfoBO.getId());
        res.setToolItemBOs(toolItemBOS);
        modelMap.addAttribute("toolInfo", res);
        modelMap.addAttribute("toolItems", toolItemBOS);
        return "toolmanage/editTool";
    }

    @RequestMapping("uhostmanage/toolManage/toolList")
    @ResponseBody
    public BaseJsonResponse toolList(ToolInfoBO toolInfoBO, Pageable pageable){
        List<ToolInfoBO> toolInfoBOList = toolInfoService.findList(toolInfoBO.toEntity(),pageable);
        long count = toolInfoService.count(toolInfoBO.toEntity());
        SimplePage<ToolInfoBO> simplePage = new SimplePage<>(toolInfoBOList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    @RequestMapping("uhostmanage/toolManage/ToolVersionList")
    @ResponseBody
    public BaseJsonResponse ToolVersionList(ToolVersionBo toolVersionBo, Pageable pageable){
        List<ToolVersionBo> toolVersionBos = toolVersionService.findList(toolVersionBo.toEntity(),pageable);
        long count = toolVersionService.count(toolVersionBo.toEntity());
        SimplePage<ToolVersionBo> simplePage = new SimplePage<>(toolVersionBos, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    /**
     * 选择测试工具后获取对应版本
     * @param toolVersionBo
     * @return
     */
    @RequestMapping("uhostmanage/toolManage/getVersionList")
    @ResponseBody
    public BaseJsonResponse getVersionList(ToolVersionBo toolVersionBo){
        if(toolVersionBo.getToolId() == null){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        toolVersionBo.setState(CfgContants.TOOL_VERSION_VALID);
        List<ToolVersion> toolVersions = toolVersionService.getVersionList(toolVersionBo.toEntity());
        List<ToolVersionBo> toolVersionBos = new ArrayList<>();
        for (ToolVersion toolVersion : toolVersions) {
            ToolVersionBo res = new ToolVersionBo(toolVersion);
            toolVersionBos.add(res);
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, toolVersionBos);
    }

    @RequestMapping("uhostmanage/toolManage/getItemList")
    @ResponseBody
    public BaseJsonResponse getItemList(@RequestParam("toolId") Integer toolId,
                                        @RequestParam(value = "taskId", required = false)Integer taskId){
        if(toolId == null && taskId == null){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        List<TestItem> toolItems = null;
        List<TestItem> taskItems = null;
        List<TestItem> resultItems = null;
        if(toolId != null){
            toolItems = toolItemService.getListByToolId(toolId);
            resultItems = toolItems;
        }
        if(taskId != null){
            taskItems = toolItemService.getListByTaskId(taskId);
            resultItems = taskItems;
        }
        /*if(CollectionUtils.isNotEmpty(toolItems) && CollectionUtils.isNotEmpty(taskItems)){
            resultItems = new ArrayList<TestItem>(taskItems);
            for(int i = 0; i < toolItems.size(); i++){
                TestItem toolItem = toolItems.get(i);
                for(int j = 0; j < taskItems.size(); j++){
                    TestItem taskItem = taskItems.get(j);
                    if(toolItem.getTestType().equals(taskItem.getTestType())){
                        toolItem.setTaskId(taskId);
                        toolItem.setTestDuration(taskItem.getTestDuration());
                        resultItems.set(j, toolItem);
                        toolItems.remove(i--);
                        break;
                    }
                }
            }
            resultItems.addAll(toolItems);
        }*/
        return new BaseJsonResponse(BaseJsonResponse.OK,resultItems);
    }


    @RequestMapping("uhostmanage/toolManage/changeState")
    @ResponseBody
    public BaseJsonResponse changeState(ToolInfoBO toolInfoBO, Pageable pageable){
        if(toolInfoBO.getId() == null || toolInfoBO.getState() == null){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        try {
            stateChange(toolInfoBO);
            toolInfoService.changeState(toolInfoBO.toEntity());
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
    }

    @RequestMapping("uhostmanage/toolManage/changeVersionState")
    @ResponseBody
    public BaseJsonResponse changeVersionState(ToolVersionBo toolVersionBo, Pageable pageable){
        if(toolVersionBo.getId() == null || toolVersionBo.getState() == null){
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        try {
            stateChange(toolVersionBo);
            toolVersionService.changeVersionState(toolVersionBo.toEntity());
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
    }

    /**
     * 切换工具版本状态
     * @param toolVersionBo
     */
    private void stateChange(ToolVersionBo toolVersionBo) {
        Integer state = toolVersionBo.getState();
        if(state.equals(CfgContants.TOOL_VERSION_INVALID) && state == CfgContants.TOOL_VERSION_INVALID){
            toolVersionBo.setState(CfgContants.TOOL_VERSION_VALID);
        }else if(state.equals(CfgContants.TOOL_VERSION_VALID) && state == CfgContants.TOOL_VERSION_VALID){
            toolVersionBo.setState(CfgContants.TOOL_VERSION_INVALID);
        }
    }

    /**
     * 切换工具状态
     * @param toolInfoBO
     */
    private void stateChange(ToolInfoBO toolInfoBO) {
        Integer state = toolInfoBO.getState();
        if(state.equals(CfgContants.TOOL_STATE_INVALID) && state == CfgContants.TOOL_STATE_INVALID){
            toolInfoBO.setState(CfgContants.TOOL_STATE_VALID);
        }else if(state.equals(CfgContants.TOOL_STATE_VALID) && state == CfgContants.TOOL_STATE_VALID){
            toolInfoBO.setState(CfgContants.TOOL_STATE_INVALID);
        }
    }

}
