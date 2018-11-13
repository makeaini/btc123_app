package com.btc123.app.utils;


import java.security.MessageDigest;

/**
 * @author liyi
 */
public class MessageDigestUtils {
	
	
	public static String encrypt(String password,String algorithm){
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] b = md.digest(password.getBytes());
			return ByteUtils.byte2HexString(b);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	public static void main(String[] args) throws Exception{
		System.out.println(MessageDigestUtils.encrypt("admin",Algorithm.SHA1));
	}
}
