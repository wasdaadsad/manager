package com.vivo.uhost.core.domain.bo;

public class TestResultBO {
    @Override
    public String toString() {
        return "TestResultBO{" +
                "id=" + id +
                ", testCase='" + testCase + '\'' +
                ", testId='" + testId + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }

    private int id;
        private String testCase;
        private String testId;
        private String testName;

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
    }
