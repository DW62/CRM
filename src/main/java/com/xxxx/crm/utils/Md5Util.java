package com.xxxx.crm.utils;

import java.security.MessageDigest;

import java.util.Base64;

/**
 */
public class Md5Util {

	/**
	 * 通过MD5算法加密字符串
	 * @param msg
	 * @return java.lang.String
	 */
	public static String encode(String msg){
		try {
			MessageDigest messageDigest=MessageDigest.getInstance("md5");
			return Base64.getEncoder().encodeToString(messageDigest.digest(msg.getBytes())) ;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(encode("123456"));
	}

}
