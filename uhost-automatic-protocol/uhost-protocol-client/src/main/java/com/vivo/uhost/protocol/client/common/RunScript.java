package com.vivo.uhost.protocol.client.common;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.FileUtils;
import com.vivo.uhost.comm.util.StreamGobbler;
import com.vivo.uhost.message.model.TestCase;
import com.vivo.uhost.message.model.TestCaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/**
 * @Author: DongJiaJin
 * @Description:执行测试用例的工具类，并且上传用例测试输出到服务器
 * @return:TestCaseInfo
 * @param:
 *      cdPath:用例文件夹的根目录
 *      testCase：用例的包装类
 * @Date: Created in 18:52 2018/5/21
 * @Modified By:
 */
public class RunScript {
    private static final Logger logger = LoggerFactory.getLogger(RunScript.class);
    public static TestCaseInfo runScript (final String cdPath, TestCase testCase) {
        final Integer caseId = testCase.getId();
        final String caseName = testCase.getCaseName();
        final String runCmd = testCase.getRunCmd();
        final Integer ovserTime = testCase.getOvertime();
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单线程
        Future<TestCaseInfo> future = threadPool.submit(new Callable<TestCaseInfo>() {
            public TestCaseInfo call() throws Exception {
                TestCaseInfo testCaseInfo = new TestCaseInfo();
                testCaseInfo.setCaseId(caseId);
                testCaseInfo.setCaseName(caseName);
                testCaseInfo.setISovertime(false);
                Runtime r = Runtime.getRuntime();
                Process p = null;
                try{
                    String ScriptPath = cdPath + "\\" + runCmd;
                    logger.info("当前运行环境为： " + System.getProperty ("os.name"));//目前只考虑windowns
                    long start = System.currentTimeMillis();
                    p = r.exec("cmd.exe /c " + ScriptPath);
                    assert p != null;
                    StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), caseId + "_" + caseName);
                    errorGobbler.start();
                    StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), caseId + "_" + caseName);
                    outGobbler.start();
                    Thread.sleep(3000);
                    p.waitFor();
                    if (System.currentTimeMillis() > start + ovserTime * 1000){
                        testCaseInfo.setISovertime(true);//超时
                    }
                }catch(Exception e){
                    logger.error("脚本运行错误:"+e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                Thread.sleep(3000);
                String result = FileUtils.readFile(Constants.UHOST_UPDATE_PACKAGE_PATH + caseId + "_" + caseName + ".txt");
                logger.info("读取脚本执行结果：" + result);

                //**********  这里将用例的输出保存到文件中后上传到服务器，返回供页面下载的url  ****************/

                String url = "http://www.baidu.com";
                testCaseInfo.setResultUrl(url);
                //***********************************************************************************/

//                FileUtil.deleteContents(new File(Constants.UHOST_UPDATE_PACKAGE_PATH + caseId + "_" + caseName + ".txt"));
                p.destroy();
                if(result.contains("true")){//这里后面做成需要判断最后三行输出中是否含有true
                    testCaseInfo.setTestResult(true);
                }else{
                    testCaseInfo.setTestResult(false);
                }
                return testCaseInfo;
            }
        });
        try {
            return future.get();//返回脚本执行的结果
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}