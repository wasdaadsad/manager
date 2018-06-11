package com.vivo.uhost.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litingfa  2018/3/26 11:23
 * @version 1.0
 * @description
 */
public class ToolInfo {

    private Long id;

    private String toolName;

    private String packageName;

    private String startCmd;

    private Date createTime;

    private Integer state;

    private List<ToolItem> toolItems;

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

    public List<ToolItem> getToolItems() {
        if(toolItems == null){
            return new ArrayList<ToolItem>();
        }
        return toolItems;
    }

    public void setToolItems(List<ToolItem> toolItems) {
        this.toolItems = toolItems;
    }

    public ToolInfo(Long id, String toolName, String packageName, String startCmd, Date createTime, Integer state, List<ToolItem> toolItems) {
        this.id = id;
        this.toolName = toolName;
        this.packageName = packageName;
        this.startCmd = startCmd;
        this.createTime = createTime;
        this.state = state;
        this.toolItems = toolItems;
    }

    public ToolInfo() {
    }
}
