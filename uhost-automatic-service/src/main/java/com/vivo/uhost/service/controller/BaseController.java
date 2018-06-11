package com.vivo.uhost.service.controller;

import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.IModelInfoService;
import com.vivo.uhost.service.IUhostInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author WangWenqian
 * @version 1.3.0
 * @describe
 * @date 2016/12/9
 */
@Component
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IUhostInfoService uhostInfoService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    protected Map<String, Object> getParamMap(HttpServletRequest request) throws IOException {
        //支持json请求
        if(request.getContentType() != null && request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)){
            return JsonUtils.parse2Map(request.getInputStream());
        }else {
            return convertMap(request.getParameterMap());
        }
    }

    protected Map<String, Object> convertMap(Map<String, String[]> parameterMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(parameterMap != null){
            for (String key : parameterMap.keySet()) {
                String[] strs = parameterMap.get(key);
                if (strs != null && strs.length > 0){
                    if(strs.length > 1){
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < strs.length; i++){
                            sb.append(strs[i]);
                            if(i != strs.length - 1){
                                sb.append(",");
                            }
                        }
                        map.put(key, sb.toString());
                    }else {
                        map.put(key, strs[0]);
                    }
                }
            }
        }
        return map;
    }


    public static void responseJson(HttpServletRequest request, HttpServletResponse response, String json){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String callback = request.getParameter("callback");
        if(StringUtils.isNotBlank(callback)){
            json = callback + "(" + json + ")";
        }
        try {
            response.getWriter().print(json);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("response json error!", e);
        }
    }

    public void addModeMap(ModelMap modelMap) {

        List<DeviceInfo> res = deviceService.findAll();

        Set<String> factorySet = new HashSet<>();
        Set<String> workShopSet = new HashSet<>();
        Set<String> agingRoomSet = new HashSet<>();
        Set<String> agingRackSet = new HashSet<>();
        Set<String> modleSet = new HashSet<>();
        Set<String> uhostSet = new HashSet<>();

        List<String> factorys = new ArrayList<>();
        List<String> workShops = new ArrayList<>();
        List<String> agingRooms = new ArrayList<>();
        List<String> agingRacks = new ArrayList<>();
        List<String> modles = new ArrayList<>();
        List<String> uhosts = new ArrayList<>();


        for (DeviceInfo re : res) {
            factorySet.add(re.getFactory());
            workShopSet.add(re.getWorkShop());
            agingRackSet.add(re.getAgingRack());
            agingRoomSet.add(re.getAgingRoom());
            modleSet.add(re.getModel());
            uhostSet.add(re.getUhost());
        }

        factorys.addAll(factorySet);
        workShops.addAll(workShopSet);
        agingRacks.addAll(agingRackSet);
        agingRooms.addAll(agingRoomSet);
        uhosts.addAll(uhostSet);
        modles.addAll(modleSet);

        modelMap.addAttribute("factorys",factorys);
        modelMap.addAttribute("workShops",workShops);
        modelMap.addAttribute("agingRacks",agingRacks);
        modelMap.addAttribute("agingRooms",agingRooms);
        modelMap.addAttribute("modles",modles);
        modelMap.addAttribute("uhosts",uhosts);
    }


    //向前端传递Uhsot模型数据
    public void addUhostModeMap(ModelMap modelMap) {

        List<UhostInfo> res = uhostInfoService.findAll();

        Set<String> factorySet = new HashSet<>();
        Set<String> workShopSet = new HashSet<>();
        Set<String> agingRoomSet = new HashSet<>();
        Set<String> agingRackSet = new HashSet<>();

        for (UhostInfo re : res) {
            factorySet.add(re.getFactory());
            workShopSet.add(re.getWorkShop());
            agingRackSet.add(re.getAgingRack());
            agingRoomSet.add(re.getAgingRoom());
        }

        List<String> factorys = new ArrayList<>();
        List<String> workShops = new ArrayList<>();
        List<String> agingRooms = new ArrayList<>();
        List<String> agingRacks = new ArrayList<>();

        factorys.addAll(factorySet);
        workShops.addAll(workShopSet);
        agingRacks.addAll(agingRackSet);
        agingRooms.addAll(agingRoomSet);

        modelMap.addAttribute("factorys",factorys);
        modelMap.addAttribute("workShops",workShops);
        modelMap.addAttribute("agingRacks",agingRacks);
        modelMap.addAttribute("agingRooms",agingRooms);
    }

}
