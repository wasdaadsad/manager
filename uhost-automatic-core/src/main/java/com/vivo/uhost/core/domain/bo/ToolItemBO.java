package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.ToolItem;

/**
 * @author litingfa  2018/3/26 11:43
 * @version 1.0
 * @description
 */
public class ToolItemBO {
    private Long id;

    private Long toolId;

    private Integer taskId;

    private Integer testNum;//测试编号

    private String testName;

    private Integer itemOrder;

    private Integer isDefault = 0;

    private Integer isLast = 0;

    private Integer testDuration;  //单位：秒

    private Integer state;

    private String caseName;

    public ToolItemBO(ToolItem toolItem) {
        this.id = toolItem.getId();
        this.toolId = toolItem.getToolId();
        this.taskId = toolItem.getTaskId();
        this.testNum = toolItem.getTestNum();
        this.testName = toolItem.getTestName();
        this.itemOrder = toolItem.getItemOrder();
        this.isDefault = toolItem.getIsDefault();
        this.isLast = toolItem.getIsLast();
        this.testDuration = toolItem.getTestDuration();
        this.state = toolItem.getState();
        this.caseName = toolItem.getCaseName();
    }

    public ToolItemBO() {
    }

    public ToolItem toEntity(){
        ToolItem toolItem = new ToolItem();
        toolItem.setId(this.id);
        toolItem.setToolId(this.toolId);
        toolItem.setTaskId(this.taskId);
        toolItem.setTestNum(this.testNum);
        toolItem.setTestName(this.testName);
        toolItem.setItemOrder(this.itemOrder);
        toolItem.setIsDefault(this.isDefault);
        toolItem.setIsLast(this.isLast);
        toolItem.setState(this.state);
        toolItem.setTestDuration(this.testDuration);
        toolItem.setCaseName(this.caseName);
        return toolItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTestNum() {
        return testNum;
    }

    public void setTestNum(Integer testNum) {
        this.testNum = testNum;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public Integer getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(Integer testDuration) {
        this.testDuration = testDuration;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}
