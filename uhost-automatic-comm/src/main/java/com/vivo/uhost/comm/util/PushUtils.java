package com.vivo.uhost.comm.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Random;

public class PushUtils {


    /**
     * 获得版本数字
     * V5.5.4 = 554,V6 = 600,V5.1 = 510
     *
     * @param version
     * @return
     */
    public static int gerVersionNumber(String version) {
        int result = 0;
        if (StringUtils.isBlank(version) || !version.startsWith("V")) {
            return -1;
        }
        try {
            String v = version.replaceAll("[^0-9]", "");
            if (NumberUtils.isDigits(v)) {
                result = Integer.parseInt(v);
                if (result > 0 && result < 10) {
                    result = result * 1000;
                }
                if (result >= 10 && result < 100) {
                    result = result * 100;
                }
                if (result >= 100 && result < 1000) {
                    result = result * 10;
                }
                if (result >= 10000) {
                    result = -1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

	/** 产生一个随机的字符串 */
	public static String getRandomString(int length) {
		String result = "888888";
		try {
			String str = "012210";
			Random random = new Random();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int num = random.nextInt(6);
				buf.append(str.charAt(num));
			}
			result = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String encryptPwd(String pwd) {
		try {
			char[] data = new char[pwd.length()];
			pwd.getChars(0, pwd.length(), data, 0);
			for (int i = 0; i < pwd.length(); i++) {
				data[i] += i;
			}
			pwd = new String(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}

	public static String reverse(String str) {
		return new StringBuffer(str).reverse().toString();
	}

	public static String object2Empty(Object temp, String defaultValue) {
		return temp == null ? defaultValue : temp.toString();
	}

	public static int integer2int(Integer temp) {
		return integer2int(temp, -1);
	}

	public static int integer2int(Integer temp, int defaultValue) {
		return temp == null ? defaultValue : temp;
	}

	public static long long2long(Long temp) {
		return long2long(temp, -1);
	}

	public static long long2long(Long temp, long defaultValue) {
		return temp == null ? defaultValue : temp;
	}

	public static float float2float(Float temp, float defaultValue) {
		return temp == null ? defaultValue : temp;
	}

	public static void main(String[] args) {
		System.out.println("random string: " + encryptPwd(getRandomString(6)));
	}
}
