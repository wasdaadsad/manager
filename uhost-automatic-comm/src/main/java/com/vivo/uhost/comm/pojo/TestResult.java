package com.vivo.uhost.comm.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/5.
 */
public class TestResult {
    private int id;
    private String testCase;
    private String testId;
    private String testName;
    private String childModel;
    private String comment;
    private String testMethod;
    private String testCmd;
    private String isSucceed;
    private String res;
    private String resultState;
    private String exceptResult;
    private Date testTime;

    @Override
    public String toString() {
        return "TestResult{" +
                "id=" + id +
                ", testCase='" + testCase + '\'' +
                ", testId='" + testId + '\'' +
                ", testName='" + testName + '\'' +
                ", childModel='" + childModel + '\'' +
                ", comment='" + comment + '\'' +
                ", testMethod='" + testMethod + '\'' +
                ", testCmd='" + testCmd + '\'' +
                ", isSucceed='" + isSucceed + '\'' +
                ", res='" + res + '\'' +
                ", resultState='" + resultState + '\'' +
                ", exceptResult='" + exceptResult + '\'' +
                ", testTime=" + testTime +
                '}';
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
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

    public String getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(String isSucceed) {
        this.isSucceed = isSucceed;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
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

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

}
