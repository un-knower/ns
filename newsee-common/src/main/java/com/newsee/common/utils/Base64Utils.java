package com.newsee.common.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;

/**
 * base64可用于url传递参数的解码编码共通类
 * @author 肖斯斯
 *
 */
public class Base64Utils {

	/*
	 * base64编码
	 * @param：需要编码的字符串
	 * @return:已经编码好的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeBase64UrlSafe(String str) throws UnsupportedEncodingException{
		String base64str = "";
		if(StringUtils.isBlank(str)){
			return base64str;
		}
		base64str = new String(Base64.encodeBase64(str.getBytes()),"UTF-8");
		base64str = base64str.replace("=", "*");
		base64str = base64str.replace("+","-");
		base64str = base64str.replace("/","_");
		return base64str;
	}
	
	/*
	 * base64解码
	 * @param：需要解码的字符串
	 * @return:已经解码好的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeBase64UrlSafe(String str) throws UnsupportedEncodingException{
		String base64str = "";
		if(StringUtils.isBlank(str)){
			return base64str;
		}
		base64str = str.replace("-","+");
		base64str = base64str.replace("_","/");
		base64str = base64str.replace("*", "=");
		base64str = new String(Base64.decodeBase64(base64str.getBytes()),"UTF-8");
		return base64str;
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException{
		String loadport = "BASUO";
		String base64String = encodeBase64UrlSafe(loadport);
		System.out.println(base64String);
		base64String = decodeBase64UrlSafe(base64String);
		System.out.println(base64String);
	}
}
