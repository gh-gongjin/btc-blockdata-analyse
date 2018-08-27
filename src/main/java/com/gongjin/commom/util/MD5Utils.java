package com.gongjin.commom.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 * @title 
 * @author 龚进
 * @date 2018年8月27日
 * @version 1.0
 */
public class MD5Utils {
	/**
	 * 生成32位大写MD5值
	 */
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * MD5加密
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
		try {
			if (str == null || str.trim().length() == 0) {
				return "";
			}
			byte[] bytes = str.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			bytes = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + "" + HEX_DIGITS[bytes[i] & 0xf]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
