package com.vivo.uhost.web.controller;

import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.core.domain.bo.SpotRecordBO;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.SpotRecordServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/17
 */
@Controller
public class FileHandleController extends BaseController {
    private static Log log = LogFactory.getLog(FileHandleController.class);
    @Autowired
    private SpotRecordServiceImpl spotRecordService;

    @RequestMapping("uhostmanage/recordManage/fileHandle")
    public String home() {
        return "bspreport/fileHandle";
    }

    //实现多文件,单字符串解析上传,对文件进行用springmvc来上传，
    @RequestMapping("uhostmanage/recordManage/autoLoad")
    public void uploadFile(@RequestParam CommonsMultipartFile[] files, HttpServletRequest req, HttpServletResponse resp, String string) throws ServletException, IOException {
        String savePath = "D:/Programs/uhost/WEB-INF/upload";
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            log.info(savePath + "目录不存在，需要创建");
            file.mkdir();
        }
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    String logname = i + "_log.txt";
                    files[i].transferTo(new File(savePath + "/" + logname));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(Calendar.getInstance().getTime());
        SpotRecordBO spotRecordBO = JsonUtils.toObject(string, SpotRecordBO.class);
        spotRecordBO.setUpdateTime(time);
        log.debug(req.getRequestURL() + "\n" + spotRecordBO.toString());
        spotRecordService.insertSpotRecord(spotRecordBO);
    }

    @RequestMapping("uhostmanage/recordManage/loadFile")
    public void loadFile(@Param("id") Integer id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SpotRecordBO spotRecordBO = spotRecordService.getBySpotRecordId(id);
        String recordUrl = spotRecordBO.getRecordUrl();
        String fileName = recordUrl.substring(recordUrl.lastIndexOf("/") + 1);
        String fileSaveRootPath = "D:/Programs/uhost/WEB-INF/upload";
        String path = findFileSavePathByFileName(fileName, fileSaveRootPath);
        File file = new File(path + "\\" + fileName);
        log.debug("文件路径" + file.getPath());
        if (!file.exists()) {
            log.debug("文件不存在");
            return;
        }
        try {
            FileInputStream in = new FileInputStream(path + "\\" + fileName);
            OutputStream out = resp.getOutputStream();
            resp.setContentType("application/force-download");
            resp.setContentType("application/octet-stream");
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findFileSavePathByFileName(String fileName, String fileSaveRootPath) {
        String dir = fileSaveRootPath + "\\";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
