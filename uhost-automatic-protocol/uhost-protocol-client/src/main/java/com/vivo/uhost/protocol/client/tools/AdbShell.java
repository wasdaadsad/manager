/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.client.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author huangxiaoqun  2018/3/13 14:57
 * @version 1.0
 * @description
 */
public class AdbShell {

    private static Process p;
    private static StringBuilder sb;
    private static String res;
    private static ArrayList<String> usbDevices;
    private static long start;
    private static int i;

    public synchronized static String exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            p = Runtime.getRuntime().exec(commandStr);
            p.waitFor();
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    res = sb.toString();
                    br.close();
                    p.destroy();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //String commandStr = "adb install -r Z:\\1635_7.1\\out-PD1635MA\\target\\product\\msm8952_64\\system\\app\\VivoTestService\\VivoTestService.apk";
        // String commandStr = "ipconfig";
        // AdbShell.exeCmd(commandStr);
        /*AdbShell.exeCmd("adb shell cat init.rc");*/
        /**//*AdbShell.exeCmd("cmd /k start F:\\opt\\BSPCheck\\BSPCheckTool\\startall_test.bat");*/
        /*AdbShell.exeCmd("adb shell \"pwd\"");*/
        sendUpdatePackage("\"D:\\My Documents\\Desktop\\PD1616B_A_A1710.30.05_eng-update-full.zip\"", "");

    }

    public static void sendUpdatePackage(final String packagePath, final String zipName) {
        String res2 = new AdbShell().exeCmd("adb devices");
        //AdbShell.exeCmd("adb -s c1a0acd3 "+"install -r Z:\\1635_7.1\\out-PD1635MA\\target\\product\\msm8952_64\\system\\app\\VivoTestService\\VivoTestService.apk");
        String devices[] = res2.split("\n");
        usbDevices = new ArrayList<String>();
        for (String e : devices) {
            String usbDevice = e.substring(0, e.indexOf("device"));
            usbDevice = usbDevice.trim();
            if (usbDevice.contains("List") == false) {
                usbDevices.add(usbDevice);
                System.out.println("连上的设备有:" + usbDevice);
                //AdbShell.exeCmd("adb -s"+usbDevice+"install -r Z:\\1635_7.1\\out-PD1635MA\\target\\product\\msm8952_64\\system\\app\\VivoTestService\\VivoTestService.apk");
            }
        }

        for (final String s : usbDevices) {
            start = System.currentTimeMillis();
            //new AdbShell().exeCmd("adb -s "+s+" push D:\\My Documents\\Desktop\\PD1616B_A_A1708.25.07_eng-update-full.zip /storage/emulated/0");
            //AdbShell.exeCmd("adb -s c1a0acd3 "+"install -r \"D:\\My Documents\\Desktop\\pmtx.apk\"");
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // new AdbShell().exeCmd("adb -s "+s+" push Z:\\1635_7.1\\out-PD1635MA\\target\\product\\msm8952_64\\system\\app\\VivoTestService\\VivoTestService.apk /storage/emulated/0");
                        //new AdbShell().exeCmd("adb -s "+s+" push D:\\My Documents\\Desktop\\PD1616B_A_A1708.25.07_eng-update-full.zip /storage/emulated/0");
                        new AdbShell().exeCmd("adb -s " + s + " push " + packagePath + " /storage/emulated/0");
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Long end = System.currentTimeMillis();
                        System.out.println(s + "push耗时:" + (end - start));
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //Thread.currentThread().
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static ArrayList<String> getAdbDevices() {
        String res2 = new AdbShell().exeCmd("adb devices");
        //AdbShell.exeCmd("adb -s c1a0acd3 "+"install -r Z:\\1635_7.1\\out-PD1635MA\\target\\product\\msm8952_64\\system\\app\\VivoTestService\\VivoTestService.apk");
        String devices[] = res2.split("\n");
        ArrayList usbDevices = new ArrayList<String>();
        for (String e : devices) {
            String usbDevice = e.substring(0, e.indexOf("device"));
            usbDevice = usbDevice.trim();
            if (usbDevice.contains("List") == false) {
                usbDevices.add(usbDevice);
            }
        }
        return usbDevices;
    }

}
