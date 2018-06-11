
package com.vivo.uhost.protocol.client.tools;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.FileUtils;
import com.vivo.uhost.comm.util.StreamGobbler;
import com.vivo.uhost.comm.util.StringUtil;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;


/**
 * 手机adb指令操作集合
 *
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/3/22
 */
public class DeviceCommProp {

    private static final Logger logger = LoggerFactory.getLogger(DeviceCommProp.class);

    private static final String EMMC_ID_PATH = "/sys/block/mmcblk0/device/cid";
    private static final String UFS_ID_PATH = "sys/ufs/ufsid";

    /**
     * 获取手机EMMCID号
     *
     * @param serial
     * @return
     */
    public static String getEmmcId(String serial) {
        String emmcId = null;
        String path = null;
        if (isUfsFileExist()) {
            path = UFS_ID_PATH;
        } else {
            path = EMMC_ID_PATH;
        }
        emmcId = AdbShell.exeCmd("adb -s " + serial + " shell cat " + path);
        if (emmcId.contains("Permission denied")) {
            try {
                DeviceCommProp.adbVivoroot(serial);
                Thread.sleep(2000);
                emmcId = AdbShell.exeCmd("adb -s " + serial + " shell cat " + path);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //logger.info("emmc:" + emmcId);
        emmcId = emmcId.replace("\r\n", "").trim();
        return emmcId;
    }

    /**
     * 获取手机机型版本
     *
     * @param serial
     * @return
     */
    public static String getModelVersion(String serial) {
        String modelVersion = "";
        modelVersion = AdbShell.exeCmd("adb -s " + serial + " shell getprop ro.vivo.product.model");
        modelVersion = modelVersion.replace("\r\n", "").trim();
        return modelVersion;
    }

    /**
     * 获取手机系统版本
     *
     * @param serial
     * @return
     */
    public static String getOsVersion(String serial) {
        String osVersion = "";
        osVersion = AdbShell.exeCmd("adb -s " + serial + " shell getprop ro.vivo.product.version");
        osVersion = osVersion.replace("\r\n", "").trim();
        return osVersion;
    }

    /**
     * adb vivoroot
     *
     * @param serial
     */
    public static void adbVivoroot(String serial) {
        AdbShell.exeCmd("adb -s " + serial + " vivoroot");
    }

    /**
     * adb devices
     * 获取PC连接手机设备
     *
     * @return 设备号数组
     */
    public static ArrayList<String> adbDevices() {
        String serialDevices = AdbShell.exeCmd("adb devices");
        String[] devices = serialDevices.split("\n");
        ArrayList usbDevices = new ArrayList<String>();
        if (devices.length > 1) {
            for (String e : devices) {
                try {
                    if (!e.contains("device")){
                        continue;
                    }
                    String usbDevice = e.substring(0, e.indexOf("device"));
                    usbDevice = usbDevice.trim();
                    if (usbDevice.contains("List") == false) {
                        usbDevices.add(usbDevice);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
//        logger.info(usbDevices.toString());
        return usbDevices;
    }

    /**
     * adb kill-server
     * 调试用，关闭adb服务
     *
     * @param serial
     * @return
     */
    public static boolean adbKillServer(String serial) {
        AdbShell.exeCmd("adb -s " + serial + " kill-server");
        logger.info("adb server is killed, connect device is " + serial + "!");
        return true;
    }

    /**
     * adb start-server
     * 调试用，开启adb服务
     *
     * @param serial
     * @return
     */
    public static boolean adbStartServer(String serial) {
        String result = AdbShell.exeCmd("adb -s " + serial + " start-server");
        if (result == null) {
            logger.info("adb server was open before!");
        } else {
            logger.info("adb server was closed before，now is rebooted!");
        }
        return true;
    }

    /**
     * adb pull
     * 只能单文件pull,adb pull 手机卡文件路径(原始路径) 需要下载文件路径（目标路径
     *
     * @param serial    :手机设备号
     * @param phonePath :手机文件路径，无法检测是否存在
     * @param diskPath  :电脑磁盘路径，可自动创建文件夹
     */
    public static void adbPullFile(final String serial, final String phonePath, final String diskPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AdbShell.exeCmd("adb -s " + serial + " pull " + phonePath + " " + diskPath);
                logger.info("adb -s " + serial + " pull " + phonePath + " " + diskPath);
            }
        }).start();

    }

    /**
     * adb push
     * 单文件push指令,adb -s 设备号 push 文件原始路径 手机文件存放路径
     * @param serial    :手机设备号
     * @param phonePath :手机文件路径，无法检测是否存在
     * @param diskPath  :电脑磁盘路径，可自动创建文件夹
     */
    public static String adbPushFile(final String serial, final String phonePath, final String diskPath) {


        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                Runtime r=Runtime.getRuntime();
                Process p=null;
                try{
                    logger.info("开始root...");
                    adbVivoroot(serial);
                    DeviceCommProp.adbRemount(serial);
                    logger.info("序列号为" + serial + "的机器正在后台push,请稍等...");
                    p = r.exec("adb -s " + serial + " push " + diskPath + " " + phonePath);
                    assert p != null;
                    StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), serial);
                    errorGobbler.start();//将输出截获到写入进临时文件serial.txt
                    StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), serial);
                    outGobbler.start();
                    p.waitFor();
                }catch(Exception e){
                    logger.info("adb push程序运行错误:"+e.getMessage());
                    e.printStackTrace();
                }
                Thread.sleep(3000);
                String result = FileUtils.readFile(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt");
                logger.info("读取push结果：  " + result);
                //删除临时文件的值
                FileUtil.deleteContents(new File(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt"));
                Boolean isUpdateOk = DeviceCommProp.deviceUpdateByTFCard(serial, new File(diskPath).getName());
                logger.info(isUpdateOk + result);
                if(p != null){
                    p.destroy();
                }
                return "广播发送是否成功：" + isUpdateOk + "   adb push 打印结果:" + result;
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * adb push
     * push apk安装包至手机中重启安装
     * @param serial    :手机设备号
     * @param phonePath :手机文件路径
     * @param diskPath  :电脑磁盘路径
     */
    public static String adbPushInstallApk(final String serial, final String phonePath, final String diskPath) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                Runtime r=Runtime.getRuntime();
                Process p=null;
                try{
                    logger.info("开始root...");
                    adbVivoroot(serial);
                    DeviceCommProp.adbRemount(serial);
                    p = r.exec("adb -s " + serial + " push " + diskPath + " " + phonePath);
                    assert p != null;
                    StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), serial);
                    errorGobbler.start();//将输出截获到写入进临时文件serial.txt
                    StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), serial);
                    outGobbler.start();
                    p.waitFor();
                }catch(Exception e){
                    logger.info("adb push程序运行错误:"+e.getMessage());
                    e.printStackTrace();
                }
                Thread.sleep(3000);
                String result = FileUtils.readFile(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt");
                logger.info("读取push结果：  " + result);
                //删除临时文件的值
                FileUtil.deleteContents(new File(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt"));
                AdbShell.exeCmd("adb -s " + serial + " reboot");//重启后会自动安装apk,adb install 还需要手动点一下
                if(p != null){
                    p.destroy();
                }
                return  "adb push 打印结果:" + result;
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * adb rm -r
     * 移除手机目标文件
     *
     * @param serial     :手机设备号
     * @param targetPath :删除手机的目标文件路径
     */
    public static void adbRemoveFile(String serial, String targetPath) {
        String result = AdbShell.exeCmd("adb -s " + serial + " shell rm -r " + targetPath);
        logger.info("adb -s " + serial + " shell rm " + targetPath);
        if ("".equals(result) || result.isEmpty()) {
            logger.info("adb -s " + serial + " shell rm " + targetPath + " success!");
        }
        if (result.contains("No such file or directory")) {
            logger.info(targetPath + "is not exists!");
        }
    }

    /**
     * adb install apk
     * 覆盖安装,需要手动确认安装,调试用,可push安装
     *
     * @param serial     :手机设备号
     * @param targetPath :apk路径
     *                   未测试
     */
    public static void adbInstall(String serial, String targetPath) {
        String result = AdbShell.exeCmd("adb -s " + serial + " install -r " + targetPath);
        if (result != null && result.length() > 0) {
            logger.info("adb -s " + serial + " install -r " + targetPath);
        }
    }

    /**
     * adb uninstall apk包名
     * 完全卸载
     * adb -s 设备号 uninstall 应用包（xxxx.xxx.com）
     *
     * @param serial
     * @param targetPath not test
     */
    public static void adbUninstall(String serial, String option, String targetPath) {
        String result = AdbShell.exeCmd("adb -s " + serial + " uninstall -" + option + " " + targetPath);
        if (result != null && result.length() > 0) {
            logger.info("adb -s " + serial + " uninstall -" + option + " " + targetPath);
        }
    }

    /**
     * adb reboot remount
     * 重启重新挂载
     *
     * @param serial not test
     */
    public static void adbRebootRemount(String serial) {
        String result = AdbShell.exeCmd("adb -s " + serial + " reboot remount");
        if (result.isEmpty()) {
            logger.info("adb -s " + serial + " reboot remount success!");
        }
    }

    /**
     * adb remount
     * 重新挂载
     *
     * @param serial not test
     */
    public static boolean adbRemount(String serial) {
        String result = AdbShell.exeCmd("adb -s " + serial + " remount");
        if (("remount succeeded").equals(result)) {
            logger.info("adb -s " + serial + " remount success!");
            return true;
        }
        return false;
    }

    /**
     * 修改权限
     *
     * @param serial
     * @param filePath not test
     */
    public static void adbChmodR777(String serial, String filePath) {
        AdbShell.exeCmd("adb -s " + serial + " shell chmod -R 777 " + filePath);
    }

    /**
     * adb get-serialno
     * 获取串口号，调试，校验识别
     *
     * @param serial not test
     */
    public static String adbGetSerialno(String serial) {
        String result = AdbShell.exeCmd("adb -s " + serial + " get-serialno");
        if (!"unknown\n".equals(result)) {
            result = result.substring(0, result.lastIndexOf("\n"));
            System.out.println(result);
            return result;
        }
        return "unknown";
    }

    /**
     * adb shell getprop persist.sys.updater.imei
     * 通过adb读取imei属性值
     * @param serial
     * @return unknown
     */
    public static String adbGetImeiNum(String serial){
        String result = AdbShell.exeCmd("adb -s " + serial + " shell getprop persist.sys.updater.imei");
        if (StringUtil.isNotBlank(result)){
            result = result.replace("\r\n","").trim();
            return result;
        }
        return "unknown";
    }


    public static boolean isUfsFileExist() {
        try {
            File f = new File(UFS_ID_PATH);
            if (f.exists()) {
                //logger.info("File " + UFS_ID_PATH + " exist!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //logger.info("File " + UFS_ID_PATH + " doesn't exist!");
        return false;
    }

    public static String readFileByLine(String file_name) {
        String string = null;
        try {
            File file = new File(file_name);
            logger.info("file_name:" + file_name);
            BufferedReader br = new BufferedReader(new FileReader(file));
            string = br.readLine();
            logger.info("string:" + string);
            br.close();
            logger.info("read: " + string);
        } catch (IOException e) {
            logger.info("read file " + file_name + " error!");
        }
        return string;
    }

    /*根据cmmc截取序列号  20-27位  如150100474536424d4203cd9dccd4a400 >> cd9dccd4*/
    public static String getSerialByEmmc(String emmc) {
        return emmc.substring(20, 28);
    }



    /*通过T卡升级
    * @serial：手机序列号
    * @updatePackageName：安装包的文件名，注意是文件名，不是安装的路径
    * @author：dongjiajin
    * */
    public static Boolean deviceUpdateByTFCard(final String serial, final String updatePackageName) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                String cmd = "adb -s " + serial + " shell am broadcast -a bbk.receiver.action.SYSTEM_UPDATE --es bbk.system.update.PACKAGE_NAME " + Constants.PHONE_UPDATE_PACKAGE_PATH + updatePackageName + "/mode=update_local";
                logger.info("开始发送升级广播：  " + cmd);
                return AdbShell.exeCmd(cmd);
            }
        });
        try {
            logger.info("升级指令返回的结果为:\n" + future.get());
            if (future.get().contains("Broadcast completed")) {
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*通过fastboot升级
    * @serial：fastboot根据线程来分开多个机器，所以序列号不需要
    * @batPath：fastboot_flash_all.bat 这个升级脚本的全路径
    * @author：dongjiajin
    * */
    public static Integer deviceUpdateByfastboot(final String serial, final String cdPath,final String comment) {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                Runtime r=Runtime.getRuntime();
                Process p=null;
                try{
                    String batPath = cdPath + "\\fastboot_flash_all.bat";
                    String shellPath = cdPath + "/fastboot_flash_all.sh";
                    logger.info("当前运行环境为： " + System.getProperty ("os.name"));
                    if(System.getProperty ("os.name").contains("Windows")){//如果是Windows环境
                        String newBatPath = FileUtils.fastbootBatProcessor(batPath,serial).getPath();
                        if(Integer.valueOf(comment) == 1){
//                            p = r.exec("cmd.exe /c echo yes | " + newBatPath);//注意这里一定要加上cmd.exe /c
                            p = r.exec("cmd.exe /c " + newBatPath + " yes");
                        }else if(Integer.valueOf(comment) == 2){
//                            p = r.exec("cmd.exe /c echo no | " + newBatPath);
                            p = r.exec("cmd.exe /c " + newBatPath + " no");
                        }
                    }else{//如果是linux环境
                        String newShellPath =  FileUtils.fastbootBatProcessor(shellPath,serial).getPath();
                        if(Integer.valueOf(comment) == 1){
//                            p = r.exec("echo yes | bash " + newShellPath);
                            p = r.exec("bash " + newShellPath + " yes");
                        }else if(Integer.valueOf(comment) == 2){
//                            p = r.exec("echo no | bash " + newShellPath);
                            p = r.exec("bash " + newShellPath + " no");
                        }
                    }
                    assert p != null;
                    StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), serial);
                    errorGobbler.start();
                    StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), serial);
                    outGobbler.start();
                    p.waitFor();
                }catch(Exception e){
                    logger.error("fastboot程序运行错误:"+e.getMessage());
                    e.printStackTrace();
                }
                Thread.sleep(3000);
                //读取临时文件的值
                String result = FileUtils.readFile(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt");
                logger.info("读取fastboot结果：" + result);
                //删除临时文件的值
                FileUtil.deleteContents(new File(Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt"));
                if(p != null){
                    p.destroy();
                }
                if(result.contains("OKAY") && result.contains("finished") && result.contains("rebooting") && result.contains("locking")){
                    return Constants.STATE_TASK_SUCCESS;
                }
                return Constants.STATE_TASK_FAILED;
            }
        });
        try {
            return future.get();//返回fastboot执行状态
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
