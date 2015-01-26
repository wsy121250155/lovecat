package com.ailk.ec.unitdesk.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类 统一桌面系统加密使用
 * 
 * @author huaqi
 * @date 2013-12-9
 */
public class Des3 {
	// 密钥
	// liuyunqiang@lx100$#365#$
	// 2013%Linkage&Asiainfo123
	private final static String secretKey = "2013%jiangsu&&iam##admin";// 3DES加密密钥
	// 向量
	private final static String iv = "76543210";// 向量
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, String secretKey)
			throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) throws Exception {

		String encrytTemp = encryptText.replace("aaabbbccc", "+");
		encrytTemp = encrytTemp.replace("dddeeefff", "=");
		// System.out.println("encrytTemp="+encrytTemp);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encrytTemp));

		return new String(decryptData, encoding);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText, String secretKey)
			throws Exception {

		String encrytTemp = encryptText.replace("aaabbbccc", "+");
		encrytTemp = encrytTemp.replace("dddeeefff", "=");
		// System.out.println("encrytTemp="+encrytTemp);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encrytTemp));

		return new String(decryptData, encoding);
	}

	public static void main(String[] args) {
		try {
			String key = "asd123";
			String enkey = Des3.encode(key);
			System.out.println("*****************加密*****************");
			System.out.println("明文: " + key);
			System.out.println("密文: " + enkey);
			System.out.println("*************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}