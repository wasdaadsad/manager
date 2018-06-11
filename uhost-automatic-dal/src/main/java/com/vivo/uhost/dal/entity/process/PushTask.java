package com.vivo.uhost.dal.entity.process;



import java.util.Date;
import java.util.List;

public class PushTask {
    private Integer id;

    private Integer pushType;

    private Integer processId;

    private Integer osVersionId;

    private Integer toolId;

    private Integer toolVersionId;

    private Integer preId;

    private Integer nextId;

    private Integer parentId;

    private Integer taskOrder;

    private Integer totalNum;

    private Integer successNum;

    private Date startTime;

    private Date updateTime;

    private Integer state;  //10 11 12 13 1

    private String versionCode;

    private String toolName;

    private List<PushTask> childTasks;

    private List<TestItem> testItems;

    private Integer testWay;

    public Integer getTestWay() {
        return testWay;
    }

    public void setTestWay(Integer testWay) {
        this.testWay = testWay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getOsVersionId() {
        return osVersionId;
    }

    public void setOsVersionId(Integer osVersionId) {
        this.osVersionId = osVersionId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public Integer getToolVersionId() {
        return toolVersionId;
    }

    public void setToolVersionId(Integer toolVersionId) {
        this.toolVersionId = toolVersionId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(Integer taskOrder) {
        this.taskOrder = taskOrder;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public List<PushTask> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(List<PushTask> childTasks) {
        this.childTasks = childTasks;
    }

    public List<TestItem> getTestItems() {
        return testItems;
    }

    public void setTestItems(List<TestItem> testItems) {
        this.testItems = testItems;
    }

	@Override
	public String toString() {
		return "PushTask [id=" + id + ", pushType=" + pushType + ", processId=" + processId + ", osVersionId="
				+ osVersionId + ", toolId=" + toolId + ", toolVersionId=" + toolVersionId + ", preId=" + preId
				+ ", nextId=" + nextId + ", parentId=" + parentId + ", taskOrder=" + taskOrder + ", totalNum="
				+ totalNum + ", successNum=" + successNum + ", startTime=" + startTime + ", updateTime=" + updateTime
				+ ", state=" + state + ", versionCode=" + versionCode + ", toolName=" + toolName + ", childTasks="
				+ childTasks + ", testItems=" + testItems + "]";
	}
    
    
}