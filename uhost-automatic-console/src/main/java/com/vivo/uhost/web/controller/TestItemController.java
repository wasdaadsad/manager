package com.vivo.uhost.web.controller;

import com.vivo.uhost.dal.entity.TestItem;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.TestItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */
@Controller
public class TestItemController extends BaseController {
    @Autowired
    TestItemServiceImpl testItemServiceImpl;
    @RequestMapping("/uhostmanage/testItem/home")
    public void test(){
     List<TestItem> testItems= testItemServiceImpl.findAll();
    }
}
