package com.vivo.uhost.protocol.client.tools;

import gnu.io.*;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 串口通信工具包
 *
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/3/29
 */
public class SerialUtils {

    private static final Logger logger = LoggerFactory.getLogger(SerialUtils.class);
    private static Map<String, String> oldMap = null;
    private static SerialPort serialPort = null;

    /**
     * 查找所有可用端口
     *
     * @return 可用端口名称列表
     */
    public static ArrayList<String> findPort() {
        //获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 打开串口
     *
     * @param portName 端口名称
     * @param baudrate 波特率
     * @return 串口对象
     */
    public static SerialPort openPort(String portName, int baudrate) {
        try {
            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);
            //判断是不是串口
            if (commPort instanceof SerialPort && portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                SerialPort serialPort = (SerialPort) commPort;
                //设置一下串口的波特率等参数
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                return serialPort;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 往串口发送数据
     *
     * @param serialPort 串口对象
     * @param order      待发送数据
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) {
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从串口读取数据
     *
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     */
    public static byte[] readFromPort(SerialPort serialPort) {
        InputStream in = null;
        byte[] bytes = null;
        try {
            in = serialPort.getInputStream();
            int bufflenth = in.available();        //获取buffer里的数据长度
            while (bufflenth != 0) {
                bytes = new byte[bufflenth];    //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 自定义串口指令实例
     *
     * @param serialPort
     * @param serialCmd
     * @param sendWaitTime
     * @param respOutTime
     * @return
     */
    public static String writeAndRead(SerialPort serialPort, String serialCmd, long sendWaitTime, long respOutTime) {
        //logger.info("serialCmd：" + serialCmd);
        serialCmd = serialCmd + "\n";
        byte[] cmdSend = serialCmd.getBytes();
        String res = null;
        try {
            serialPort.enableReceiveTimeout(2000);
            sendToPort(serialPort, cmdSend);
            Thread.sleep(sendWaitTime);
            long start = System.currentTimeMillis();
            byte[] result = readFromPort(serialPort);
            while ((respOutTime - sendWaitTime) > (System.currentTimeMillis() - start)) {
                if (result != null) {
                    res = new String(result);
                    res = res.replace("\r\n", "").trim();
                    logger.info("relust：" + res);
                    return res;
                }
            }
            logger.info("serialCmd TimeOut!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SerialCmdEntity 封装传入
     *
     * @param serialPort
     * @param serialCmdEntity
     * @return
     */
    public static synchronized String writeAndRead(SerialPort serialPort, SerialCmdEntity serialCmdEntity) {
        String serialCmd = serialCmdEntity.getCmd() + "\n";
        byte[] cmdSend = serialCmd.getBytes();
        //logger.info("serialCmd：" + serialCmd);
        String res = null;
        try {
            serialPort.enableReceiveTimeout(2000);
            sendToPort(serialPort, cmdSend);
            Thread.sleep(serialCmdEntity.getSendWaitTime());
            long start = System.currentTimeMillis();
            byte[] result = readFromPort(serialPort);
            while ((serialCmdEntity.getRespOutTime() - serialCmdEntity.getSendWaitTime()) > (System.currentTimeMillis() - start)) {
                if (result != null) {
                    res = new String(result);
                    res = res.replace("\r\n", "").trim();
                    logger.info("relust：" + res);
                    return res;
                }
            }
            logger.info("serialCmd TimeOut!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭串口
     *
     * @param serialPort 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 手机连入后传入对应emmcs集合
     * 获取emmc和serialPort匹配项
     * 这个需要定时执行，非常耗时，需要看情况定时-需要优化
     *
     * @param emmcs
     * @return
     */
    public static Map<String, String> getEmmcAndSerialPortNameMap(List<String> emmcs) {
        oldMap = new HashMap<>();
        SerialCmdEntity serialCmdEntity = new SerialCmdEntity("AT+BKRID=1", 200, 400);
        boolean repeatFlag = false;
        List<String> mapEmmc = new ArrayList<>();
        do {
            ArrayList<String> oldPortNameList = SerialUtils.findPort();
            if (oldPortNameList.size() > 0 && emmcs.size() > 0) {
                outterLoop:
                for (String port : oldPortNameList) {
                    ArrayList<String> newPortNameList0 = SerialUtils.findPort();
                    if (!ListUtils.isEqualList(oldPortNameList,newPortNameList0)) {
                        break outterLoop;
                    }
                    try {
                        serialPort= SerialUtils.openPort(port, 115200);
                    }catch (Exception e){
                        serialPort.close();
                        break outterLoop;
                    }
                    ArrayList<String> newPortNameList1 = SerialUtils.findPort();
                    if (serialPort == null){
                        continue;
                    }
                    for (String emmc : emmcs) {
                        ArrayList<String> newPortNameList2 = SerialUtils.findPort();
                        if (ListUtils.isEqualList(newPortNameList1,newPortNameList2)) {
                            String res = SerialUtils.writeAndRead(serialPort, serialCmdEntity);
                            if (res != null && res.contains(emmc)) {
                                logger.info("emmc:" + emmc + "," + "port:" + port);
                                oldMap.put(emmc, port);
                                mapEmmc.add(emmc);
                            } else {
                                continue;
                            }
                            repeatFlag = false;
                        } else {
                            mapEmmc.clear();
                            serialPort.close();
                            repeatFlag = true;
                            break outterLoop;
                        }
                    }
                    serialPort.close();
                }
            }
        } while (repeatFlag);

        if (!ListUtils.isEqualList(mapEmmc, emmcs)) {
            for (String emmcId : emmcs) {
                if (!mapEmmc.contains(emmcId)) {
                    oldMap.put(emmcId, "0");
                }
            }
        }

        return oldMap;
    }

    /**
     * 单个手机设备发送 单条AT指令 默认时间
     *
     * @param portName  串口通信端口号
     * @param serialCmd 默认 1000 2000
     * @return
     */
    public static String onSendOneSerialCmd(String portName, String serialCmd) {
        if (portName != null && !"0".equals(portName)) {
            SerialPort serialPort = SerialUtils.openPort(portName, 115200);
            if (serialPort != null) {
                String res = writeAndRead(serialPort, serialCmd, 1000, 2000);
                serialPort.close();
                return res;
            }
            serialPort.close();
        }
        return null;
    }

    /**
     * 单个手机设备发送 单条AT指令实例
     *
     * @param portName  串口通信端口号
     * @param cmdEntity 1000 2000 自定义时间
     * @return
     */
    public static String onSendOneSerialCmd(String portName, SerialCmdEntity cmdEntity) {
        if (portName != null && !"0".equals(portName)) {
            SerialPort serialPort = SerialUtils.openPort(portName, 115200);
            if (serialPort != null) {
                String res = writeAndRead(serialPort, cmdEntity);
                serialPort.close();
                return res;
            }
            serialPort.close();
        }
        return null;
    }


    /**
     * 单个手机设备发送 多条AT指令实例
     *
     * @param map
     * @param deviceEmmc
     * @param serialCmdEntities
     * @return 测试结果集合
     */
    public static List<String> onSendOneSerialCmd(Map<String, String> map, String deviceEmmc, List<SerialCmdEntity> serialCmdEntities) {
        //Map<String,String> map = SerialUtils.getEmmcAndSerialPortNameMap(emmcs);
        String port = map.get(deviceEmmc);
        List<String> results = new ArrayList<>();
        if (port != null && !"0".equals(port)) {
            SerialPort serialPort = SerialUtils.openPort(port, 115200);
            if (serialPort != null) {
                for (SerialCmdEntity cmdEntity : serialCmdEntities) {
                    String res = writeAndRead(serialPort, cmdEntity);
                    results.add(res);
                }
                serialPort.close();
                return results;
            }
            serialPort.close();
        }
        return null;
    }

    /**
     * 根据传入的emmcList获取map匹配进行测试
     * 发送SerialCmd指令
     *
     * @param map
     * @param cmdEntity 500 1000
     */
    public static void onSendListSerialCmd(Map<String, String> map, SerialCmdEntity cmdEntity) {
        for (String key : map.keySet()) {
            String port = map.get(key);
            logger.info("emmc:" + key + "," + "prot:" + port);
            if (port != null && !"0".equals(port)) {
                SerialPort serialPort = SerialUtils.openPort(port, 115200);
                if (serialPort != null) {
                    String res = writeAndRead(serialPort, cmdEntity);
                    System.out.println(res);
                }
                serialPort.close();
            }
        }
    }
}
