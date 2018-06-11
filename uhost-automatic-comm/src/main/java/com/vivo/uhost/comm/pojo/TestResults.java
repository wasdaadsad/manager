package com.vivo.uhost.comm.pojo;

import com.vivo.uhost.comm.pojo.TestResult;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
public class TestResults {
    @Override
    public String toString() {
        return "TestResults{" +
                "testResults=" + testResults +
                '}';
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

    private List<TestResult> testResults;
}
