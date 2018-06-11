package com.vivo.uhost.message.codec;


import com.vivo.uhost.comm.util.ByteUtils;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.message.Message;

import java.io.UnsupportedEncodingException;

public class PackageDecoder {

	public static Message decode(byte[] b){
		Message message = null;
		try {

			byte[] cmdTypeArray = new byte[4];
			System.arraycopy(b, 0, cmdTypeArray, 0, 4);
			int cmdType = ByteUtils.byte2int(cmdTypeArray);

			message = new Message(cmdType);

			if(cmdType != Constants.CMD_TYPE_INVALID){
				byte[] pkgLenArray = new byte[4];
				System.arraycopy(b, 4, pkgLenArray, 0, 4);
				int pkgLen = ByteUtils.byte2int(pkgLenArray);

				byte[] packetArray = new byte[pkgLen];
				System.arraycopy(b, 8, packetArray, 0, pkgLen);
				message.setMsg(new String(packetArray, "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				message = new Message(Constants.CMD_TYPE_INVALID, new String(b, "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return message;
	}

}
