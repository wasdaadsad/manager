package com.vivo.uhost.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MD5Utils {
    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

	public static String MD5(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
            return toHexString(md);
		} catch (Exception e) {
			return null;
		}
	}

    public static String MD5(File file){
        if(file == null){
            return null;
        }
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            return toHexString(md);
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateSign(List<String> args){
        Collections.sort(args, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char c1 = o1.length() > 0 ? o1.charAt(o1.length() - 1) : ' ';
                char c2 = o2.length() > 0 ? o2.charAt(o2.length() - 1) : ' ';
                return c1 - c2;
            }
        });
        return MD5(StringUtil.concat(args.toArray()));
    }

    private static String toHexString(byte[] md){
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}
