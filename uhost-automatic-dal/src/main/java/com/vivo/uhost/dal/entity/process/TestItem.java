package com.vivo.uhost.dal.entity.process;

import com.vivo.uhost.dal.entity.ToolItem;

public class TestItem {
    private Integer id;

    private Integer toolId;

    private Integer taskId;

    private Integer testType;

    private String itemName;

    private Integer preId;

    private Integer nextId;

    private Integer itemOrder;

    private Integer isDefault = 0;

    private Integer isLast = 0;

    private Integer testDuration;  //单位：秒

    private Integer state;

    private String caseName;

    public TestItem() {
    }

    public TestItem(ToolItem toolItem) {
        this.id = Integer.valueOf(toolItem.getId().toString());
        this.toolId = Integer.valueOf(toolItem.getToolId().toString());
        this.testType = toolItem.getTestNum();
        this.itemName = toolItem.getTestName();
        this.itemOrder = toolItem.getItemOrder();
        this.isDefault = toolItem.getIsDefault();
        this.isLast = toolItem.getIsLast();
        if (toolItem.getTestDuration() != null){
            int td = toolItem.getTestDuration();
            this.testDuration = td/3600;
        }else{
            this.testDuration = toolItem.getTestDuration();
        }
        this.state = toolItem.getState();
        this.caseName = toolItem.getCaseName();
    }

    public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
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
}