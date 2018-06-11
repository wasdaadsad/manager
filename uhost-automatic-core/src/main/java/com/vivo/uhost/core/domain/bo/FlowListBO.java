/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */


package com.vivo.uhost.core.domain.bo;


import com.vivo.uhost.dal.entity.FlowList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Locale;

public class FlowListBO {

    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";

    private static Log log = LogFactory.getLog(FlowListBO.class);

    private long id;
    private String flowName;//流程名
    private String model ;//机型
    private String upgrade;//升级
    private String processType;//流程测试
    private String test;//测试
    private String factoryUpgrade ;//出厂升级
    private String factoryReset;//恢复出厂设置
    private String startTime;//开始时间
    private String updateTime;//更新时间
    private String sums;//总数
    private String succeed;//成功数
    private String status;//状态
    private String statusVP;//状态展示
    private String remark;//描述
    private int offset;
    private int pageSize;

    public FlowListBO(FlowList flowList) {
        this.id = flowList.getId();
        this.flowName = flowList.getFlowName();
        this.model = flowList.getModel();
        this.sums = flowList.getSums();
        this.succeed = flowList.getSucceed();
        this.remark = flowList.getRemark();
        this.status = flowList.getStatus();
        if (flowList.getStartTime() != null) {
            this.startTime = new DateFormatter(PATTEN).print(flowList.getStartTime(), Locale.CHINA);
        }
        if (flowList.getUpdateTime() != null) {
            this.updateTime = new DateFormatter(PATTEN).print(flowList.getUpdateTime(), Locale.CHINA);
        }
        this.processType = flowList.getProcessType();
        if(StringUtils.isNotBlank(this.processType)){
            String[] tests = StringUtils.split(processType,",");
            for (String s : tests) {
                switch (s) {
                    case "11":
                        upgrade = "√";
                        break;
                    case "20":
                        test = "√";
                        break;
                    case "14":
                        factoryUpgrade = "√";
                        break;
                    case "15":
                        factoryReset = "√";
                        break;
                    default:
                        break;
                }
            }
        }
        switch (flowList.getStatus()) {
            case "10"://-1
                statusVP = "未开始";
                break;
            case "11"://1
                statusVP = "运行中";
                break;
            case "13"://0
                statusVP = "已完成";
                break;
            case "12"://2
                statusVP = "已关闭";
                break;
            default:
                break;
        }
    }

    public FlowList toEntity(){
        FlowList flowList = new FlowList();
        flowList.setId( this.id);
        flowList.setFlowName(this.flowName);
        flowList.setModel(this.model);
        try {
            if (this.startTime != null) {
                flowList.setStartTime(DateUtils.parseDate(startTime, PATTEN));
            }
            if(this.updateTime != null){
                flowList.setUpdateTime(DateUtils.parseDate(updateTime, PATTEN));
            }
        } catch (ParseException e) {
            log.error("日期解析失败");
            throw new RuntimeException("日期解析失败");
        }
        flowList.setSums(this.sums);
        flowList.setSucceed(this.succeed);
        flowList.setStatus(this.status);
        flowList.setProcessType(this.processType);
        flowList.setRemark(this.remark);
        return flowList;
    }

    public FlowListBO() {
    }

    @Override
    public String toString() {
        return "FlowListBO{" +
                "id=" + id +
                ", flowName='" + flowName + '\'' +
                ", model='" + model + '\'' +
                ", upgrade='" + upgrade + '\'' +
                ", test='" + test + '\'' +
                ", factoryUpgrade='" + factoryUpgrade + '\'' +
                ", factoryReset='" + factoryReset + '\'' +
                ", startTime='" + startTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", sums='" + sums + '\'' +
                ", succeed='" + succeed + '\'' +
                ", status='" + status + '\'' +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getStatusVP() {
        return statusVP;
    }

    public void setStatusVP(String statusVP) {
        this.statusVP = statusVP;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFactoryUpgrade() {
        return factoryUpgrade;
    }

    public void setFactoryUpgrade(String factoryUpgrade) {
        this.factoryUpgrade = factoryUpgrade;
    }

    public String getFactoryReset() {
        return factoryReset;
    }

    public void setFactoryReset(String factoryReset) {
        this.factoryReset = factoryReset;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSums() {
        return sums;
    }

    public void setSums(String sums) {
        this.sums = sums;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
