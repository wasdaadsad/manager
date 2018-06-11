package com.vivo.uhost.comm.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import static org.apache.commons.io.FileUtils.directoryContains;
import static org.apache.commons.io.FileUtils.sizeOf;

public class FileUtils {
    private static final Log log = LogFactory.getLog(FileUtils.class);

	public static String readFile(String fileName) {
		String result = StringUtils.EMPTY;
		try {
			File file = new File(fileName);
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void writeToFile(String lineStr, String file, Boolean isAppend) {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(file, isAppend));
			bWriter.write(lineStr);
			bWriter.newLine();
			bWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下载文件
	public static void downloadFile(String path, String url) {
		HttpClient client = null;
		try {
			// 创建HttpClient对象
			client = new DefaultHttpClient();
			// 获得HttpGet对象
			// HttpGet httpGet = getHttpGet(url, null, null);
			HttpGet httpGet = new HttpGet(url);
			// 发送请求获得返回结果
			HttpResponse response = client.execute(httpGet);
			// 如果成功
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte[] result = EntityUtils.toByteArray(response.getEntity());
				BufferedOutputStream bw = null;
				try {
					// 创建文件对象
					File f = new File(path);
					// 创建文件路径
					if (!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					// 写入文件
					bw = new BufferedOutputStream(new FileOutputStream(path));
					bw.write(result);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (bw != null)
							bw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {// 如果失败
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append("httpStatus:");
				errorMsg.append(response.getStatusLine().getStatusCode());
				errorMsg.append(response.getStatusLine().getReasonPhrase());
				errorMsg.append(", Header: ");
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					errorMsg.append(header.getName());
					errorMsg.append(":");
					errorMsg.append(header.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.getConnectionManager().shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param filename
	 *            目标文件
	 * @param charset
	 *            目标文件的编码格式
	 */
	public static void readDesc(String filename, String charset) {

		RandomAccessFile rf = null;
		try {
			long l1 = System.currentTimeMillis();
			rf = new RandomAccessFile(filename, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			System.out.println("start------" + start);
			System.out.println("nextend------" + nextend);
			String line;
			rf.seek(nextend);
			int c = -1;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if (line != null) {
						System.out.println(new String(line.getBytes("ISO-8859-1"), charset));
					} else {
						System.out.println(line);
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					// System.out.println(rf.readLine());
					System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), charset));
				}
			}
			System.out.println("elapsed: " + (System.currentTimeMillis() - l1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rf != null)
					rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void readBuffer(String file){
		try {
			System.out.println("-------start-----" + DateUtils.format(new Date()));
			long l1 = System.currentTimeMillis();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(file)));
			BufferedReader in = new BufferedReader(new InputStreamReader(bis, Constants.DEFAULT_ENCODING), 10 * 1024 * 1024);// 10M缓存
			String deviceInfo = null;
			while (in.ready()) {
				deviceInfo = in.readLine();
			}
			System.out.println("elapsed: " + (System.currentTimeMillis() - l1));
			System.out.println(deviceInfo + "-------end-----" + DateUtils.format(new Date()));
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

    /**
     * 多级目录创建
     *
     * @param folderPath
     *            准备要在本级目录下创建新目录的目录路径例如 c:myf
     * @param paths
     *            无限级目录参数，各级目录以单数线区分 例如 a|b|c
     * @return 返回创建文件后的路径
     */
    public static String createFolders(String folderPath, String paths) {
        String txts = folderPath;
        try {
            String txt;
            txts = folderPath;
            StringTokenizer st = new StringTokenizer(paths, "|");
            while(st.hasMoreTokens()){
                txt = st.nextToken().trim();
                txts = createFolder(txts + txt + "/");
            }
        } catch (Exception e) {
            System.out.println("创建文件夹出错");
            log.error("创建文件夹出错："+e.getMessage());
        }
        return txts;
    }

    /**
     * 新建目录
     *
     * @param folderPath
     *            目录
     * @return 返回目录创建后的路径
     * 只能创建一级目录,如果上级目录不存在,无法创建
     */
    public static String createFolder(String folderPath) {
        String txt = folderPath;
        try {
            File myFilePath = new File(txt);
            txt = folderPath;
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            log.error("创建目录操作出错："+e.getMessage());
        }
        return txt;
    }


	/**
	 * 解压tar.gz 文件
	 * @author：dongjiajin
	 * @param fileDir      要解压的tar.gz文件的路径
	 * @param outputDir    要解压到某个指定的目录下
	 */
	public static String unTarGz(String fileDir, String outputDir) throws IOException {
		File file = new File(fileDir);
		String pathWithName = outputDir + file.getName();
		String path = pathWithName.substring(0,pathWithName.length()- 7);//把文件后缀tar.gz去掉后的文件名作为解压后的文件夹名
		boolean isExit = directoryContains(new File(outputDir), new File(path));
		log.info(isExit);
		if(!isExit){//如果输出目录中不存在解压后的文件，才需要解压
			log.info("解压缩后的文件路径" + path);
			log.info("fastboot升级包正在解压，请稍后...");
			TarInputStream tarIn = null;
			try {
				tarIn = new TarInputStream(new GZIPInputStream(
						new BufferedInputStream(new FileInputStream(file))),
						1024 * 2);

				createDirectory(outputDir, null);//创建输出目录

				TarEntry entry = null;
				while ((entry = tarIn.getNextEntry()) != null) {

					if (entry.isDirectory()) {//是目录
						entry.getName();
						createDirectory(outputDir, entry.getName());//创建空目录
					} else {//是文件
						File tmpFile = new File(outputDir + "/" + entry.getName());
						createDirectory(tmpFile.getParent() + "/", null);//创建输出目录
						OutputStream out = null;
						try {
							out = new FileOutputStream(tmpFile);
							int length = 0;

							byte[] b = new byte[2048];

							while ((length = tarIn.read(b)) != -1) {
								out.write(b, 0, length);
							}
						} catch (IOException ex) {
							throw ex;
						} finally {
							if (out != null)
								out.close();
						}
					}
				}
			} catch (IOException ex) {
				throw new IOException("解压归档文件出现异常", ex);
			} finally {
				try {
					if (tarIn != null) {
						tarIn.close();
					}
				} catch (IOException ex) {
					throw new IOException("关闭tarFile出现异常", ex);
				}
			}
		}else{
			log.info(path + "  文件已经存在，无需重复解压");
		}
		return path;
	}

	/**
	 * 构建目录
	 * @author：dongjiajin
	 * @param outputDir
	 * @param subDir
	 */
	public static void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.mkdirs();
		}
	}

	/*
	 * 下载超大文件
	 *  @urlPath： 下载的Url路径
	 *  @uhostDir：放在哪个目录下
	 *  @author：dongjiajin
	 * */
	public static File downloadLagerFile(String urlPath, String uhostDir) {
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			conn.getResponseCode();
			String imgurl = conn.getURL().toString();
			final String updatePackageName = imgurl.substring(imgurl.lastIndexOf("/") + 1);
			String filePath2 = uhostDir + updatePackageName;
			File file2 = new File(filePath2);

			conn.setConnectTimeout(10 * 1000);
		  	long size = Long.parseLong(conn.getHeaderField("Content-Length"));


			boolean b = directoryContains(new File(uhostDir), new File(filePath2));
			log.info("远程文件大小：" + size);
			log.info("文件是否存在" + b);
			log.info("本地文件大小" + file2.length());
			if(b && size == file2.length()){//如果文件存在且大小等于远程文件的大小
				log.info(file2.getName() + ":  文件已经存在，且大小为:  " + size + "不需要重复下载");
			}else{
				log.info("安装包存放路径为：" + filePath2);
				log.info("正在下载,请稍等...");
				org.apache.commons.io.FileUtils.copyURLToFile(url, file2);
				log.info("下载完成");
			}
			return file2;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}




	/*
	 *  修改fastboot升级文件
	 *  @path： bat或者sh文件的路径
	 *  @serial：待升级的手机的序列号
	 *  @author：dongjiajin
	 * */
	public static File fastbootBatProcessor(String path, String serial) throws IOException {
		//读取文件
		File file=new File(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		//内存流
		CharArrayWriter caw=new CharArrayWriter();
		//替换
		String line=null;
		//以行为单位进行遍历
		while((line=br.readLine())!=null){
			//替换每一行中符合被替换字符条件的字符串
			line=line.replaceAll("adb reboot", "adb -s " + serial + " reboot")
					.replaceAll("fastboot bbk","fastboot -s " + serial + " bbk")
					.replaceAll("fastboot flash","fastboot -s " + serial + " flash ")
					.replaceAll("fastboot erase","fastboot -s " + serial + " erase ")
					.replaceAll("fastboot reboot","fastboot -s " + serial + " reboot ")
					.replaceAll("fastboot -S 200M flash","fastboot -s " + serial + " -S 400M flash ");
			if(System.getProperty ("os.name").contains("Windows")){
				if (line.contains("var=no")){
					line = "set var=%1";
				}
				if (line.contains("set /p var=")){
					line = "echo %1";
				}
				if (line.contains("pause")){
					line = "";
				}
			}else{
				line=line.replaceAll("read result","")
						.replace("$result","$1");
				if (line.contains("echo -n")){
					line = "echo $1";
				}
			}
			//将该行写入内存
			caw.write(line);
			//添加换行符，并进入下次循环
			caw.append(System.getProperty("line.separator"));
		}
		//关闭输入流
		br.close();
		//将内存中的流写入新的文件，新的bat文件的名字加"-serial"
		if(System.getProperty ("os.name").contains("Windows")){//如果是Windows环境
			String newFile = file.getParent() + "\\fastboot_flash_all_" + serial +".bat";
			FileWriter fw=new FileWriter(newFile);
			caw.writeTo(fw);
			fw.close();
			return new File(newFile);
		}else{//如果是linux环境
			String newFile = file.getParent() + "/fastboot_flash_all_" + serial +".sh";
			FileWriter fw=new FileWriter(newFile);
			caw.writeTo(fw);
			fw.close();
			return new File(newFile);
		}
	}
}
