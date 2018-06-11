package com.vivo.uhost.message.model;

import java.util.List;

/**
 * @Author: DongJiaJin
 * @Description:BSP点检测试用例集的实体类
 * @Date: Created in 10:18 2018/5/21
 * @Modified By:
 */
public class TestCaseCollection {
    private String caseListName;        //用例集名称
    private Integer caseNumber;         //所含用例数量
    private Integer caseListPlatform;   //用例集适用平台           0/1/2  对应  QCOM/MTK/QCOM and MTK
    private List<TestCase> caseList;    //所含测试用例列表

    public TestCaseCollection() {
    }

    public TestCaseCollection(String caseListName, Integer caseNumber, Integer caseListPlatform, List<TestCase> caseList) {
        this.caseListName = caseListName;
        this.caseNumber = caseNumber;
        this.caseListPlatform = caseListPlatform;
        this.caseList = caseList;
    }

    public String getCaseListName() {
        return caseListName;
    }

    public void setCaseListName(String caseListName) {
        this.caseListName = caseListName;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Integer getCaseListPlatform() {
        return caseListPlatform;
    }

    public void setCaseListPlatform(Integer caseListPlatform) {
        this.caseListPlatform = caseListPlatform;
    }

    public List<TestCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<TestCase> caseList) {
        this.caseList = caseList;
    }

    @Override
    public String toString() {
        return "TestCaseCollection{" +
                "caseListName='" + caseListName + '\'' +
                ", caseNumber=" + caseNumber +
                ", caseListPlatform=" + caseListPlatform +
                ", caseList=" + caseList +
                '}';
    }
}