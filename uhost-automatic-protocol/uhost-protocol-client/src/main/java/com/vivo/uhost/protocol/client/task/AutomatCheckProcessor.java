package com.vivo.uhost.protocol.client.task;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.model.*;
import com.vivo.uhost.protocol.client.CfgUtils;
import com.vivo.uhost.protocol.client.ClientConstants;
import com.vivo.uhost.protocol.client.MessageSender;
import com.vivo.uhost.protocol.client.common.RunScript;
import com.vivo.uhost.protocol.client.tools.UhostProcessor;
import com.vivo.uhost.protocol.core.model.mq.TestCaseNotice;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: DongJiaJin
 * @Description:
 * @Date: Created in 11:39 2018/5/21
 * @Modified By:
 */
public class AutomatCheckProcessor implements Callable<TestCaseNotice> {
    private static final Logger logger = LoggerFactory.getLogger(AutomatCheckProcessor.class);
    private PushTaskReq pushTaskReq;
    public AutomatCheckProcessor(PushTaskReq pushTaskReq) {
        this.pushTaskReq = pushTaskReq;
    }

    @Override
    public TestCaseNotice call() throws Exception {
        TestCaseNotice testCaseNotice = null;
        try {
            testCaseNotice = doTask(pushTaskReq);
        } catch (Exception ex) {
            logger.error("run push task failed!", ex);
        } finally {
            try {
                /*
                * 上报自动化点检的任务执行状态
                *
                * 注意：消息体格式发生了变化，不再是PushTaskResp和TaskStateNotice两个，而是TestCaseNotice
                * 所以：server在netty和redis两个处理类中需要做判断，来分开处理
                *
                * */
                String sendMethod = CfgUtils.getString(ClientConstants.DEVICE_TASK_PROCESS, "channel");
                if (sendMethod.equals("topic")) {
                    //使用redis的发布订阅模式来上报设备当前测试任务的状态
                    RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
//                    redisQProducer.sendCheckTaskState(testCaseNotice);
                } else {
                    //通过netty Channel来上报设备当前测试任务的状态
//                    MessageSender.reportCheck(testCaseNotice);
                    logger.info("send device check task state succeed!",testCaseNotice);
                }
            } catch (Exception ex) {
                logger.error("send device check task state failed!", ex);
            }
        }
        return null;
    }

    private TestCaseNotice doTask(PushTaskReq pushTaskReq){
        Integer msgId = pushTaskReq.getMsgId();//消息ID
        final String content = pushTaskReq.getContent();//推送内容 -> 所有测试用例的json串
        TestCaseCollection testCaseCollection = JsonUtils.toObject(content, TestCaseCollection.class);
        final List<FutureTask> result = new ArrayList<>();
        final List<Map<TestCase,String>> TestCaseAndPath = new ArrayList<>();
        for (final TestCase testCase : testCaseCollection.getCaseList()) {
            Callable task = new Callable<Map<TestCase,String>>() {
                public Map<TestCase,String> call() throws Exception {
                    String cdPath = UhostProcessor.downloadCaseAndUncompression(testCase.getUrl());//并行下载&解压
                    Map<TestCase, String> map = new HashMap<>();
                    map.put(testCase,cdPath);
                    return map;
                }
            };
            FutureTask futureTask = new FutureTask(task);
            Thread pAccountThread = new Thread(futureTask);
            logger.info("futureTask线程现在开始启动，启动时间为：" + System.nanoTime());
            pAccountThread.start();
            logger.info("主线程开始执行其他任务");
            result.add(futureTask);
        }
        for (FutureTask futureTask : result) {
            while (!futureTask.isDone()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("futureTask线程下载解压完毕，此时时间为" + System.nanoTime());
            try {
                Map<TestCase,String> testMap = (Map<TestCase,String>) futureTask.get();
                TestCaseAndPath.add(testMap);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        List<TestCaseInfo> testCaseInfos = new ArrayList<>();
        for (Map<TestCase,String> map : TestCaseAndPath){//串行阻塞模式，避免用例相互冲突
            for (Map.Entry<TestCase, String> entry : map.entrySet()){
                logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                TestCaseInfo testCaseInfo = RunScript.runScript(entry.getValue(),entry.getKey());//串行执行测试用例脚本
                testCaseInfos.add(testCaseInfo);
            }
        }
        TestCaseNotice testCaseNotice = new TestCaseNotice();
        testCaseNotice.setMsgId(msgId);
        testCaseNotice.setToken(MessageSender.getToken());
        testCaseNotice.setResult(testCaseInfos);
        return testCaseNotice;
    }
}