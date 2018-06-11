package com.vivo.uhost.message.model;

/**
 * @Author: DongJiaJin
 * @Description:单个测试用例的测试结果实体类
 * @Date: Created in 14:40 2018/5/21
 * @Modified By:
 */
public class TestCaseInfo {
    private Integer caseId;                 //用例id
    private String caseName;                //用例名称
    private Boolean ISovertime;             //是否超时
    private Boolean testResult;             //测试结果：成功为true，失败为false
    private String resultUrl;               //用例控制台输出的下载路径（uhost会将用例输出写入文件并上传到服务端，提供下载链接）

    public TestCaseInfo() {
    }

    public TestCaseInfo(Integer caseId, String caseName, Boolean ISovertime, Boolean testResult, String resultUrl) {
        this.caseId = caseId;
        this.caseName = caseName;
        this.ISovertime = ISovertime;
        this.testResult = testResult;
        this.resultUrl = resultUrl;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Boolean getISovertime() {
        return ISovertime;
    }

    public void setISovertime(Boolean ISovertime) {
        this.ISovertime = ISovertime;
    }

    public Boolean getTestResult() {
        return testResult;
    }

    public void setTestResult(Boolean testResult) {
        this.testResult = testResult;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    @Override
    public String toString() {
        return "TestCaseInfo{" +
                "caseId=" + caseId +
                ", caseName='" + caseName + '\'' +
                ", ISovertime=" + ISovertime +
                ", testResult=" + testResult +
                ", resultUrl='" + resultUrl + '\'' +
                '}';
    }
}