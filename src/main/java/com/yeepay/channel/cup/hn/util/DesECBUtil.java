package com.yeepay.channel.cup.hn.util;

import java.security.Key;  

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;

/**
 * Description: DES对称ECB模式加解密工具类
 * @author Kevin
 * @Createdate 2014年8月1日.
 **/
public class DesECBUtil {

	/**
	 * 加密数据
	 * @param encryptString 注意：这里的数据长度只能为8的倍数
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String encryptStr, String encryptKey)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(encryptStr.getBytes());
		return ConvertUtil.bytesToHexString(encryptedData);
	}

	/**
	 * 自定义一个key
	 * 
	 * @param string
	 */
	public static byte[] getKey(String keyRule) {
		Key key = null;
		byte[] keyByte = keyRule.getBytes();
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[8];
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
			byteTemp[i] = keyByte[i];
		}
		key = new SecretKeySpec(byteTemp, "DES");
		return key.getEncoded();
	}

	/***
	 * 解密数据
	 * 
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString, String decryptKey)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte decryptedData[] = cipher.doFinal(ConvertUtil.hexStringToByte(decryptString));
		return new String(decryptedData);
	}

	public static void main(String[] args) throws Exception {
		String clearText = "springsk"; // 这里的数据长度必须为8的倍数
		String key1 = "12345678";
		String key2 = "876543210";
		System.out.println("明文：" + clearText + "\n密钥：" + key1);
		String encryptText1 = encryptDES(clearText, key1);
		System.out.println("加密后：" + encryptText1);
		String decryptText1 = decryptDES(encryptText1, key1);
		System.out.println("解密后：" + decryptText1);
		
		System.out.println("明文：" + clearText + "\n密钥：" + key2);
		String encryptText = encryptDES(clearText, key2);
		System.out.println("加密后：" + encryptText);
		String decryptText = decryptDES(encryptText, key2);
		System.out.println("解密后：" + decryptText);
	}
}
