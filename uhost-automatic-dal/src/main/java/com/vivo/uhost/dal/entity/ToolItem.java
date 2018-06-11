package com.vivo.uhost.dal.entity;

import com.vivo.uhost.dal.entity.process.TestItem; /**
 * @author litingfa  2018/3/26 11:26
 * @version 1.0
 * @description
 */
public class ToolItem {

    private Long id;

    private Long toolId;

    private Integer taskId;

    private Integer testNum;

    private String testName;

    private Integer itemOrder;

    private Integer isDefault = 0;

    private Integer isLast = 0;

    private Integer testDuration;  //单位：秒

    private Integer state;

    private String caseName;

    public ToolItem() {
    }

    public ToolItem(TestItem testItem) {
        this.caseName = testItem.getCaseName();
        this.testName = testItem.getItemName();
        this.itemOrder = testItem.getItemOrder();
        this.testDuration = testItem.getTestDuration() * 60 * 60;
        this.isLast = testItem.getIsLast();
        this.isDefault = testItem.getIsDefault();
        this.testNum = testItem.getTestType();
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
