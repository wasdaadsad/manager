package com.vivo.uhost.message.codec;

import com.vivo.uhost.comm.util.ByteUtils;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.message.Message;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class PackageEncoder {

	public static byte[] encode(Message message) {
		try {
			return encode(message.getCmdType(), message.getMsg().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	public static byte[] encode(int cmdType, byte[] array) {
		try {
			int arrayLength = array.length;
			
			ByteBuffer buff = ByteBuffer.allocate(8 + arrayLength);
			buff.put(ByteUtils.int2byte(cmdType));
			buff.put(ByteUtils.int2byte(arrayLength));
			buff.put(array);
			byte[] temp = buff.array();
			buff = null;
			return temp;
		} catch (Exception e) {
			return ByteUtils.int2byte(Constants.CMD_TYPE_INVALID);
		}
	}

}
