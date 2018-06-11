package com.vivo.uhost.core.domain.rabbit;

/**
 * Created by Administrator on 2018/1/31.
 */
public class Item {
    private int id;
    private String testName;
    private String childModel;
    private String testMethod;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", childModel='" + childModel + '\'' +
                ", testMethod='" + testMethod + '\'' +
                ", testCmd='" + testCmd + '\'' +
                ", state=" + state +
                ", resultState='" + resultState + '\'' +
                ", exceptResult='" + exceptResult + '\'' +
                ", supportPhone='" + supportPhone + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }

    private String testCmd;
    private int state;
    private String resultState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getChildModel() {
        return childModel;
    }

    public void setChildModel(String childModel) {
        this.childModel = childModel;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getTestCmd() {
        return testCmd;
    }

    public void setTestCmd(String testCmd) {
        this.testCmd = testCmd;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public String getExceptResult() {
        return exceptResult;
    }

    public void setExceptResult(String exceptResult) {
        this.exceptResult = exceptResult;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private String exceptResult;
    private String supportPhone;
    private long lastUpdateTime;
    private int offset;
    private int pageSize;


}
