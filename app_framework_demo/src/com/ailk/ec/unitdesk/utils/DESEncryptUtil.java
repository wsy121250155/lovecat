package com.ailk.ec.unitdesk.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.rt.BASE64Decoder;
import com.rt.BASE64Encoder;



/**
 * DES对称加密算法
 * 
 * 加密:encrypt(String srcStr, String key) 解密:descrypt(String encryptStr,String
 * key ) 加密解密过程中采用的key是一样的
 * 
 * @author zhaoxin
 * 
 */
public class DESEncryptUtil {
	private static String TAG = "DESEncryptUtil";

	private final static String charset = "UTF-8";
	private final static String algorithm = "DES";

	/**
	 * 加密
	 * 
	 * @param srcStr
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 */
	public static String encrypt(String srcStr, String key) {
		String strEncrypt = null;
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			strEncrypt = base64en.encode(encryptByte(srcStr.getBytes(charset),
					key));
		} catch (Exception e) {
			Log.e("加密异常");
		}
		return strEncrypt;
	}

	/**
	 * 解密
	 * 
	 * @param encryptStr
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文
	 */
	public static String decrypt(String encryptStr, String key) {
		BASE64Decoder base64De = new BASE64Decoder();
		String strDecrypt = null;
		try {
			strDecrypt = new String(decryptByte(
					base64De.decodeBuffer(encryptStr), key), charset);
		} catch (Exception e) {
			Log.e("解密异常");
		} finally {
			base64De = null;
		}
		return strDecrypt;
	}

	/**
	 * 字节加密
	 * 
	 * @param srcByte
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 */
	public static byte[] encryptByte(byte[] srcByte, String key) {
		byte[] byteFina = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
			byteFina = cipher.doFinal(srcByte);
		} catch (Exception e) {
			Log.e("字节加密异常");
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 字节解密
	 * 
	 * @param encryptByte
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文
	 */
	public static byte[] decryptByte(byte[] encryptByte, String key) {
		Cipher cipher;
		byte[] decryptByte = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
			decryptByte = cipher.doFinal(encryptByte);
		} catch (Exception e) {
			Log.e("字节解密异常");
		} finally {
			cipher = null;
		}
		return decryptByte;

	}

	/**
	 * 根据传入的字符串的key生成key对象
	 * 
	 * @param strKey
	 */
	public static Key generateKey(String strKey) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes(charset));
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(algorithm);
			return keyFactory.generateSecret(desKeySpec);
		} catch (Exception e) {
		}
		return null;

	}

	/**
	 * 利用MD5进行加密
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 *             没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException
	 */
	public static String encoderByMd5(String souceStr) throws Exception {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(souceStr.getBytes());
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
															// >>>
															// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			throw e;
		}
		return s;
	}

	/**
	 * 测试函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String key = "a4si8Ds027bT";
		String src = "test001&&123456";

		System.out.println("密钥:" + key);
		System.out.println("明文:" + src);

		String strEnc = DESEncryptUtil.encrypt(src, key);
		System.out.println("加密后,密文:" + strEnc);

		String strDes = DESEncryptUtil.decrypt(strEnc, key);
		System.out.println("解密后,明文:" + strDes);

	}

}
