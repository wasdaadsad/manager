package com.vivo.uhost.protocol.client.common;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.protocol.client.tools.AdbShell;
import com.vivo.uhost.protocol.client.tools.DeviceCommProp;
import com.vivo.uhost.protocol.client.tools.UhostProcessor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;


/*
 *  fastboot升级处理器
 *  @serial： 手机的序列号（fastboot升级不需要）
 *  @content：要下载的包的url路径（即topic的content字段）
 *  @author：dongjiajin
 * */
public class FastbootUpdateProcessor {
    public static Integer process(String serial, String content,String comment) throws IOException {

        String cdPath =  UhostProcessor.downloadFileAndUncompression(content);
        while (true){
            Long size = FileUtils.sizeOfDirectory(new File(cdPath));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Long newSize = FileUtils.sizeOfDirectory(new File(cdPath));
          if (newSize.equals(size)){//解压缩后的包，3秒钟之内大小没有变化，表示解压缩完毕，否则需要等待解压完毕
              break;
          }
        }
        return DeviceCommProp.deviceUpdateByfastboot(serial,cdPath,comment);
    }
}
