package com.vivo.uhost.web.controller;

import com.vivo.uhost.dal.entity.TestResult;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestResultController extends BaseController {
    @Autowired
    TestResultServiceImpl testResultService;

    @RequestMapping("/uhostmanage/recordManage/details")
    @ResponseBody
    public List<TestResult> testItems(@RequestParam String testId, Pageable pageable) {
        TestResult testResult = new TestResult();
        testResult.setTestId(testId);
        List<TestResult> testItems = testResultService.findList(testResult, pageable);
        return testItems;
    }
}
