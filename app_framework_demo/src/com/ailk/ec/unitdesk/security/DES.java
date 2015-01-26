package com.ailk.ec.unitdesk.security;
/**
 * DES加密算法
 */



import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DES {
	 private static String DESKey = "wt0806ae"; // 字节数必须是8的倍数  
	 private static byte[] iv1 = {(byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF};
	 
	 
	 public static void main(String[] args) throws Exception {
		
		/*
		public static String APKURLENCODE="aY8zegoXWexxvGN9bQUHrX+MfruqjLTwssX3/b0Rk+j7uxhHHfIxhQ==";
	    public static String APPURLENCODE="aY8zegoXWexxvGN9bQUHrX+MfruqjLTwUodiKF+SwEDzQbDa4rzlVDyq7WADLUrN";
	    */
		 
		DES des = new DES();
		//http://202.102.116.105:9080/MobilePortal/
		//http://202.102.116.105:9080/release/
		String enstr=des.encode("http://202.102.116.105:9080/MobilePortal");
		System.out.println("======[Key]==========:"+enstr);
		
		String xx=des.decode("aY8zegoXWexxvGN9bQUHrX+MfruqjLTwUodiKF+SwEDzQbDa4rzlVDyq7WADLUrN");
		System.out.println(xx);
		
		
	    enstr=des.encode("http://202.102.116.105:9080/release");
		System.out.println("======[Key]==========:"+enstr);
		
		xx=des.decode("aY8zegoXWexxvGN9bQUHrX+MfruqjLTwssX3/b0Rk+j7uxhHHfIxhQ==");
		System.out.println(xx);
	} 
	 
	 
	 public byte[] desEncrypt(byte[] plainText) throws Exception  
	    {
	    	IvParameterSpec iv = new IvParameterSpec(iv1);	    	 
	        DESKeySpec dks = new DESKeySpec(DESKey.getBytes());  
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
	        SecretKey key = keyFactory.generateSecret(dks);  
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	        byte data[] = plainText;
	        byte encryptedData[] = cipher.doFinal(data);
	        return encryptedData;
	    }
	      
	    public String encode(String input)   
	    {  
	    	String result = "input";
	        try {
				result = base64Encode(desEncrypt(input.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}  
			return result;
	    }  
	    
	    public String decode(String input)   
	    {  
	    	String result = "";
	        try {
	        	byte[] bt = base64Decode(input.getBytes());
				result=new String(desDecrypt(bt));
			} catch (Exception e) {
				e.printStackTrace();
			}  
			return result;
	    }  
	      
	    public  String base64Encode(byte[] s)   
	    {  
	        if (s == null)  
	            return null;  
	        return Base64.encodeToString(s, Base64.DEFAULT);

	    }  
	    
	    public  byte[] base64Decode(byte[] s)   
	    {  
	        if (s == null)  
	            return null;  
	        return Base64.decode(s, Base64.DEFAULT);

	    }  
	    
	    /**  
	     * DES算法，解密  
	     *  
	     * @param data 待解密字符串  
	     * @param key  解密私钥，长度不能够小于8位  
	     * @return 解密后的字节数组  
	     * @throws Exception 异常  
	     */  
    
		 public byte[] desDecrypt(byte[] data) throws Exception  
		    {
		        try  
		        {   
		        	IvParameterSpec iv = new IvParameterSpec(iv1);	 
			        DESKeySpec dks = new DESKeySpec(DESKey.getBytes());  
			        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			        SecretKey key = keyFactory.generateSecret(dks);
		            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		            cipher.init(Cipher.DECRYPT_MODE, key,iv);
		            return cipher.doFinal(data);
		        } catch (Exception e){   
		            throw new Exception(e);   
		        }
		    }
}




