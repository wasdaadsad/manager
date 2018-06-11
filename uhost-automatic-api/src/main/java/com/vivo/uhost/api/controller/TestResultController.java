package com.vivo.uhost.api.controller;

import com.vivo.uhost.comm.pojo.TestResult;
import com.vivo.uhost.comm.pojo.TestResults;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
@Controller
@RequestMapping("/testItem")

public class TestResultController extends BaseController {

    @Autowired
    private TestResultServiceImpl testResultService;

    @RequestMapping("/results")
    public void test(@RequestBody String testItemsStr){
        TestResults testResults=JsonUtils.toObject(testItemsStr, TestResults.class);
        List<TestResult> testResults1=testResults.getTestResults();
        for (TestResult testResult: testResults1) {
            testResultService.add(testResult);
        }
    }

}
