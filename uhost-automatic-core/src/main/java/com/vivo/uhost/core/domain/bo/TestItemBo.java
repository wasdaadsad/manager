package com.vivo.uhost.core.domain.bo;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/16.
 */
public class TestItemBo {


    /**
     * Created by Administrator on 2018/1/16.
     */
    public class TestItem {
        private int id;
        private String testName;
        private String childModel;

        @Override
        public String toString() {
            return "TestItem{" +
                    "id=" + id +
                    ", testName='" + testName + '\'' +
                    ", childModel='" + childModel + '\'' +
                    ", testMethod='" + testMethod + '\'' +
                    ", testCmd='" + testCmd + '\'' +
                    ", state=" + state +
                    ", supportPhone='" + supportPhone + '\'' +
                    ", lastUpdateTime=" + lastUpdateTime +
                    '}';
        }

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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSupportPhone() {
            return supportPhone;
        }

        public void setSupportPhone(String supportPhone) {
            this.supportPhone = supportPhone;
        }

        public Date getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Date lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        private String testMethod;
        private String testCmd;
        private String state;
        private String supportPhone;
        private Date lastUpdateTime;
    }

}
