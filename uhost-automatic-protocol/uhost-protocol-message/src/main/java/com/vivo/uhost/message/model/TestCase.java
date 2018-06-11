package com.vivo.uhost.message.model;

/**
 * @Author: DongJiaJin
 * @Description:BSP点检测试用例的实体类
 * @Date: Created in 10:04 2018/5/21
 * @Modified By:
 */
public class TestCase {

    private Integer id;                     //用例id
    private Integer caseType;               //用例类型                  0/1/2   对应   BSP/驱动/其他
    private String caseModule;              //用例模块
    private String caseName;                //用例名称
    private Integer casePlatform;           //用例适用平台               0/1/2   对应   QCOM/MTK/QCOM and MTK
    private int overtime;                   //测试预估用时(单位：s)
    private Integer caseEnv;                //用例执行的系统环境          0/1/2    对应    windowns/linux/windowns and linux
    private String runCmd;                  //用例的启动脚本             初期可以规定启动脚本必须放在文件夹的根目录   ./run.bat
    private String url;                     //用例的下载路径

    public TestCase() {
    }

    public TestCase(Integer id, Integer caseType, String caseModule, String caseName, Integer casePlatform, int overtime, Integer caseEnv, String runCmd, String url) {
        this.id = id;
        this.caseType = caseType;
        this.caseModule = caseModule;
        this.caseName = caseName;
        this.casePlatform = casePlatform;
        this.overtime = overtime;
        this.caseEnv = caseEnv;
        this.runCmd = runCmd;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getCaseModule() {
        return caseModule;
    }

    public void setCaseModule(String caseModule) {
        this.caseModule = caseModule;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Integer getCasePlatform() {
        return casePlatform;
    }

    public void setCasePlatform(Integer casePlatform) {
        this.casePlatform = casePlatform;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public Integer getCaseEnv() {
        return caseEnv;
    }

    public void setCaseEnv(Integer caseEnv) {
        this.caseEnv = caseEnv;
    }

    public String getRunCmd() {
        return runCmd;
    }

    public void setRunCmd(String runCmd) {
        this.runCmd = runCmd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", caseType=" + caseType +
                ", caseModule='" + caseModule + '\'' +
                ", caseName='" + caseName + '\'' +
                ", casePlatform=" + casePlatform +
                ", overtime=" + overtime +
                ", caseEnv=" + caseEnv +
                ", runCmd='" + runCmd + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}