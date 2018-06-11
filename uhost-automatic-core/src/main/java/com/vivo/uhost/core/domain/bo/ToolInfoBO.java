package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.ToolInfo;
import com.vivo.uhost.dal.entity.ToolItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author litingfa  2018/3/26 11:42
 * @version 1.0
 * @description
 */
public class ToolInfoBO {
    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";

    private Long id;

    private String toolName;

    private String packageName;

    private String startCmd;

    private Date createTime;

    private Integer state;

    private String stateVP;

    private String createTimeVp;

    private List<ToolItemBO> toolItemBOs;

    public ToolInfoBO(ToolInfo toolInfo) {
        this.id = toolInfo.getId();
        this.toolName = toolInfo.getToolName();
        this.packageName = toolInfo.getPackageName();
        this.startCmd = toolInfo.getStartCmd();
        this.createTime = toolInfo.getCreateTime();
        this.state = toolInfo.getState();
        if(toolInfo.getCreateTime() != null){
            this.createTimeVp = new DateFormatter(PATTEN).print(toolInfo.getCreateTime(), Locale.CHINA);
        }
        if(CollectionUtils.isNotEmpty(toolInfo.getToolItems())){
            for (ToolItem toolItem : toolInfo.getToolItems()) {
                ToolItemBO toolItemBO = new ToolItemBO(toolItem);
                this.toolItemBOs.add(toolItemBO);
            }
        }
        if(toolInfo.getState() != null){
            switch (toolInfo.getState()){
                case 1:
                    stateVP = "有效";
                    break;
                case 0:
                    stateVP = "无效";
                    break;
                default:
                    stateVP = "未知";
            }
        }
    }

    public ToolInfoBO() {
    }

    public String getStateVP() {
        return stateVP;
    }

    public void setStateVp(String stateVP) {
        this.stateVP = stateVP;
    }

    public String getCreateTimeVp() {
        return createTimeVp;
    }

    public void setCreateTimeVp(String createTimeVp) {
        this.createTimeVp = createTimeVp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStartCmd() {
        return startCmd;
    }

    public void setStartCmd(String startCmd) {
        this.startCmd = startCmd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<ToolItemBO> getToolItemBOs() {
        return toolItemBOs;
    }

    public void setToolItemBOs(List<ToolItemBO> toolItemBOs) {
        this.toolItemBOs = toolItemBOs;
    }

    public ToolInfo toEntity() {
        ToolInfo toolInfo = new ToolInfo();
        toolInfo.setId(this.id);
        toolInfo.setToolName(this.toolName);
        toolInfo.setCreateTime(this.createTime);
        toolInfo.setPackageName(this.packageName);
        toolInfo.setStartCmd(this.startCmd);
        toolInfo.setState(this.state);
        if(CollectionUtils.isNotEmpty(this.toolItemBOs)){
            List<ToolItem> toolItems = toolInfo.getToolItems();
            for (ToolItemBO toolItemBO : toolItemBOs) {
                toolItems.add(toolItemBO.toEntity());
            }
        }
        return toolInfo;
    }
}
