package com.vivo.uhost.dal.entity;

/**
 * @author litingfa  2018/3/23 15:34
 * @version 1.0
 * @description
 */
public class ProcessDetail {

    private Long id;
    private Long processId;
    private int type;//测试类型(1升级2测试3出厂升级4恢复出厂)
    private int order;
    private int failStrategy;
    private Long overTime;
    private int status;
    private int successNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getFailStrategy() {
        return failStrategy;
    }

    public void setFailStrategy(int failStrategy) {
        this.failStrategy = failStrategy;
    }

    public Long getOverTime() {
        return overTime;
    }

    public void setOverTime(Long overTime) {
        this.overTime = overTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }
}
