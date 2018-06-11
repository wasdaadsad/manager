
package com.vivo.uhost.protocol.client.task;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.message.model.DeviceInfo;
import com.vivo.uhost.message.model.PushTaskReq;
import com.vivo.uhost.message.model.PushTaskResp;
import com.vivo.uhost.protocol.client.CfgUtils;
import com.vivo.uhost.protocol.client.ClientConstants;
import com.vivo.uhost.protocol.client.MessageSender;
import com.vivo.uhost.protocol.client.common.TCardUpdateProcessor;
import com.vivo.uhost.protocol.client.common.FastbootUpdateProcessor;
import com.vivo.uhost.protocol.client.mq.TopicPushTaskConsumer;
import com.vivo.uhost.protocol.client.tools.*;
import com.vivo.uhost.message.model.DeviceStateInfo;
import com.vivo.uhost.protocol.core.model.mq.TaskStateNotice;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author huangxiaoqun  2018/3/21 15:44
 * @version 1.0
 * @description 执行测试任务
 */
public class PushTaskProcessor implements Callable<TaskStateNotice> {

    private static Logger logger = LoggerFactory.getLogger(TopicPushTaskConsumer.class);

    private PushTaskReq pushTaskReq;

    public PushTaskProcessor(PushTaskReq pushTaskReq) {
        this.pushTaskReq = pushTaskReq;
    }

    @Override
    public TaskStateNotice call() throws Exception {
        TaskStateNotice taskStateNotice = null;
        try {
            taskStateNotice = doTask(pushTaskReq);
        } catch (Exception ex) {
            logger.error("run push task failed!", ex);
        } finally {
            try {
                //上报任务执行状态
                String sendMethod = CfgUtils.getString(ClientConstants.DEVICE_TASK_PROCESS, "channel");
                if (sendMethod.equals("topic")) {
                    //使用redis的发布订阅模式来上报设备当前测试任务的状态
                    RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                    redisQProducer.sendTaskState(taskStateNotice);
                } else {
                    //通过netty Channel来上报设备当前测试任务的状态
                    PushTaskResp pushTaskResp = new PushTaskResp();
                    assert taskStateNotice != null;
                    pushTaskResp.setMsgId(taskStateNotice.getMsgId());
                    pushTaskResp.setToken(taskStateNotice.getToken());
                    pushTaskResp.setResult(taskStateNotice.getResult());
                    MessageSender.report(pushTaskResp);
                    logger.info("send device state change succeed!",pushTaskResp);
                }
            } catch (Exception ex) {
                logger.error("send device state change failed!", ex);
            }
        }
        return null;
    }


    /**
     * 执行任务测试任务
     *
     * @param pushTaskReq
     * @return
     */
    private TaskStateNotice doTask(PushTaskReq pushTaskReq) throws IOException {

        Integer msgId = pushTaskReq.getMsgId();//消息ID
        final Integer pushType = pushTaskReq.getPushType();//推送类型
        final String productName = pushTaskReq.getProductName();//执行任务的机型
        List<String> emmcidList = pushTaskReq.getEmmcidList();//执行测试任务的emmcid 设置指定编号的手机进行测试
        List<DeviceInfo> deviceInfoList = pushTaskReq.getDeviceInfoList();//获取相关的设备信息
        final String content = pushTaskReq.getContent();//推送内容
        final String comment = pushTaskReq.getComment();//推送备注

        //存储设备任务状态信息
        final List<DeviceStateInfo> result = new ArrayList<>();
        //创建一个设备状态信息和设备执行结果相互映射的map
        Map<DeviceStateInfo, FutureTask> taskMap = new HashMap<DeviceStateInfo, FutureTask>();

        //遍历设备列表同时执行任务
        for (final DeviceInfo deviceInfo : deviceInfoList) {
            final DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
            deviceStateInfo.setEmecid(deviceInfo.getEmmcId());
            /*单独创建一个Callable线程*/
            Callable task = new Callable<Integer>() {
                public Integer call() throws Exception {
                    return executeTask(pushType, content, comment,deviceInfo);
                }
            };
            FutureTask futureTask = new FutureTask(task);
            // 使用futureTask创建一个线程
            Thread pAccountThread = new Thread(futureTask);
            logger.info("futureTask线程现在开始启动，启动时间为：" + System.nanoTime());
            pAccountThread.start();
            logger.info("主线程开始执行其他任务");
            taskMap.put(deviceStateInfo,futureTask);//键为设备状态信息，值为单个设备的线程对象
        }


        //遍历taskMap,等待FutureTask任务完成（isDone）之后再返回
        for (Map.Entry<DeviceStateInfo, FutureTask> entry : taskMap.entrySet()) {
            logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());

            while (!entry.getValue().isDone()) {//如果任务没有执行完毕，线程休眠500ms
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("futureTask线程计算完毕，此时时间为" + System.nanoTime());
            try {
                //任务完成后更新设备状态信息
                entry.getKey().setState((Integer)entry.getValue().get());//设备任务状态为线程的返回状态值
                //将任务已完成的设备的状态添加到result中
                result.add(entry.getKey());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }

        logger.info("数据准备完毕,开始上报状态");
        TaskStateNotice taskStateNotice = new TaskStateNotice();
        taskStateNotice.setToken(MessageSender.getToken());
        taskStateNotice.setMsgId(msgId);
        taskStateNotice.setResult(result);
        return taskStateNotice;
    }


    //返回单个设备任务处理状态  1：成功STATE_TASK_SUCCESS， 2：失败STATE_TASK_FAILED
    public static Integer executeTask(Integer pushType,String content,String comment,DeviceInfo deviceInfo) throws IOException {
        String serial = deviceInfo.getSerial();
        DeviceCommProp.adbVivoroot(serial);
        logger.info("该手机的序列号为： " + serial);
//        if (!productName.equals(DeviceCommProp.getModelVersion(serial))) {//机型不匹配
//            logger.info("机型不匹配:获取到的机型为" + DeviceCommProp.getModelVersion(serial) + " ,传过来的机型为： " + productName);
//            return Constants.STATE_TASK_FAILED;
//        }
        if (pushType.equals(Constants.PUSH_TYPE_UPDATE)) {//系统升级
            if (Integer.valueOf(comment) == 0) {//T卡升级
                return TCardUpdateProcessor.process(serial, content);
            } else if (Integer.valueOf(comment) == 1 || Integer.valueOf(comment) == 2) {//fastboot升级-烧写分区1-yes || 2-no
                return FastbootUpdateProcessor.process(serial, content,comment);
            }else {
                return Constants.STATE_TASK_FAILED;
            }

        }
        if (pushType.equals(Constants.PUSH_TYPE_INSTALL)) {//下载并安装apk
            return UhostProcessor.downloadApkAndInstallToPhone(serial, content, Constants.UHOST_UPDATE_PACKAGE_PATH, Constants.PHONE_APK_PACKAGE_PATH);
        }
        if (pushType.equals(Constants.PUSH_TYPE_RUN_TEST)) {//启动测试
            return Constants.STATE_TASK_SUCCESS;
        }
        if (pushType.equals(Constants.PUSH_TYPE_FACTORY_UPDATE)) {//出厂系统更新
            if (Integer.valueOf(comment) == 0) {//T卡升级
                return TCardUpdateProcessor.process(serial, content);
            } else if (Integer.valueOf(comment) == 1 || Integer.valueOf(comment) == 2) {//fastboot升级-烧写分区1-yes || 2-no
                return FastbootUpdateProcessor.process(serial, content,comment);
            }else {
                return Constants.STATE_TASK_FAILED;
            }
        }
        if (pushType.equals(Constants.PUSH_TYPE_FACTORY_RESET)) {//恢复出厂设置
            //默认广播升级
            //1、工模图标存在或不存在：发消息后，手机掉线，端口重新检测，手机有个重新登录上下线的过程，丢失权限，其他都在
            if (Integer.valueOf(comment) == 0){
                DeviceCommProp.adbVivoroot(serial);
                String result = AdbShell.exeCmd("adb -s " + serial + " shell am broadcast -a android.intent.action.MASTER_CLEAR");
                if (result.contains("Broadcast completed")) {
                    return Constants.STATE_TASK_SUCCESS;
                }else {
                    return Constants.STATE_TASK_FAILED;
                }
            }else if (Integer.valueOf(comment) == 1){//AT指令恢复出厂设置
                //2、工模图标存在:发消息后,手机掉线，端口重现检测，adb在，端口在，丢失权限，其他都在
                SerialCmdEntity serialCmdEntity = new SerialCmdEntity("AT+BKRTALL=1",1000,2000);
                String res = SerialUtils.onSendOneSerialCmd(deviceInfo.getPortName(),serialCmdEntity);
                if (res.contains("OK")){
                    return Constants.STATE_TASK_SUCCESS;
                }else {
                    return Constants.STATE_TASK_FAILED;
                }
            }else {
                return Constants.STATE_TASK_FAILED;
            }
        }
        return null;
    }
}
