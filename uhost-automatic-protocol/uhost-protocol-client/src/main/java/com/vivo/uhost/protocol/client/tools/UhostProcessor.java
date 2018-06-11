package com.vivo.uhost.protocol.client.tools;

import com.vivo.uhost.comm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import static com.vivo.uhost.comm.util.FileUtils.downloadLagerFile;
import static com.vivo.uhost.comm.util.FileUtils.unTarGz;

public class UhostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(UhostProcessor.class);

    /**
     * @param serial   手机的序列号
     * @param urlPath  下载路径
     * @param uhostDir 安装包在Uhost上存放的目录
     * @param phoneDir 安装包在手机上存放的目录
     * @return 返回下载文件
     */
    public static Integer downloadFileAndPushToPhone(final String serial, String urlPath, final String uhostDir, final String phoneDir) {

        final String fileName = Objects.requireNonNull(downloadLagerFile(urlPath, uhostDir)).getName();


        String result =  DeviceCommProp.adbPushFile(serial, phoneDir, uhostDir + fileName);
        logger.info("升级结果为：" + result);
        assert result != null;
        if (result.contains("true") && result.contains(new File(uhostDir + fileName).length() + " bytes in")){
            return Constants.STATE_TASK_SUCCESS;
        }
        return Constants.STATE_TASK_FAILED;
    }



    /*
    * 下载文件并解压
    * @param urlPath  下载路径
    * @return 返回解压后的文件的路径
    * @author：dongjiajin
    * */
    public static String downloadFileAndUncompression(String urlPath) throws IOException {

        String fileName = Objects.requireNonNull(downloadLagerFile(urlPath, Constants.UHOST_UPDATE_PACKAGE_PATH)).getName();
        return unTarGz(Constants.UHOST_UPDATE_PACKAGE_PATH + fileName, Constants.UHOST_UPDATE_PACKAGE_PATH);
    }




    /**
     * @param serial   手机的序列号
     * @param urlPath  下载路径
     * @param uhostDir apk在Uhost上存放的目录
     * @param phoneDir apk在手机上存放的目录
     * @return 返回apk安装是否成功
     */
    public static Integer downloadApkAndInstallToPhone(final String serial, String urlPath, final String uhostDir, final String phoneDir) {
        final String fileName = Objects.requireNonNull(downloadLagerFile(urlPath, uhostDir)).getName();
        String result =  DeviceCommProp.adbPushInstallApk(serial, phoneDir, uhostDir + fileName);
        logger.info("升级结果为：" + result);
        assert result != null;
        if (result.contains(new File(uhostDir + fileName).length() + " bytes in")){
            logger.info("安装apk成功！");
            return Constants.STATE_TASK_SUCCESS;
        }
        logger.error("安装apk失败！");
        return Constants.STATE_TASK_FAILED;
    }


    /*
     * 下载测试用例压缩包并解压（不同的后缀调用不同的解压程序）
     * @param urlPath  下载路径
     * @return 返回解压后的文件的路径
     * @author：dongjiajin
     * */
    public static String downloadCaseAndUncompression(String urlPath) throws IOException {
        String suffix = urlPath.substring(urlPath.lastIndexOf(".") + 1);
        String fileName = Objects.requireNonNull(downloadLagerFile(urlPath, Constants.UHOST_TEST_CASE_PATH)).getName();
        switch (suffix) {
            case "gz":
                return unTarGz(Constants.UHOST_TEST_CASE_PATH + fileName, Constants.UHOST_TEST_CASE_PATH);
            case "rar":
                return null;//rar的解压程序后面加
            case "zip":
                return null;//zip的解压程序后面加
            default:
                logger.error("暂不支持后缀为" + suffix + "压缩文件的解压");
                return null;
        }
    }
}