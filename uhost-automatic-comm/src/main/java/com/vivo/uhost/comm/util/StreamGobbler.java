package com.vivo.uhost.comm.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 使用Java来执行bat命令，如果bat操作时间过长，有可能导致阻塞问题，而且不会执行bat直到关闭服务器
 *一般java的exec是没有帮你处理线程阻塞问题的，需要手动处理。
 * 大概原因是，调用Runtime.getRuntime().exec()后，如果不及时捕捉进程的输出，会导致JAVA挂住，看似被调用进程没退出。
 * 所以，解决办法是，启动进程后，再启动两个JAVA线程及时的把被调用进程的输出截获
 *
 * */

/**
 * @author：dongjiajin
 * 用于处理Runtime.getRuntime().exec产生的错误流及输出流
 */
public class StreamGobbler extends Thread {
    InputStream is;
    String serial;
    OutputStream os;

    public StreamGobbler(InputStream is, String serial) {
        this(is, serial, null);
    }

    StreamGobbler(InputStream is, String serial, OutputStream redirect) {
        this.is = is;
        this.serial = serial;
        this.os = redirect;
    }

    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            if (os != null)
                pw = new PrintWriter(os);

            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
                //这里需要区分不同的缓冲区，否则多线程同时操作同一个文件会导致不可预料的异常
                FileUtils.writeToFile(line,Constants.UHOST_UPDATE_PACKAGE_PATH + serial + ".txt",true);
                System.out.println(line);
            }

            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
