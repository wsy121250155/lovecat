package com.mobile.base.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import static com.mobile.base.commons.Zip.*;

import com.mobile.base.commons.verify.DesUtil;

public class Serialize {	
	/**
	 * 序列化写入
	 * @param obj
	 * @param ops
	 * @throws Exception
	 */
	public static void serialize(Object obj, OutputStream out) throws Exception {
		ObjectOutputStream oops = new ObjectOutputStream(out);
		oops.writeObject(obj);
		oops.close();
		out.close();
	}
	
	/**
	 * 将Object转化为byte数组
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static byte[] serialize(Object obj) throws Exception {
		ByteArrayOutputStream btout = new ByteArrayOutputStream();
		serialize(obj, btout);
		return btout.toByteArray();
	}
	
	/**
	 * 反序列化读入
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static Object unserialize(InputStream in) throws Exception {
		ObjectInputStream oin = new ObjectInputStream(in);
		Object result = oin.readObject();
		oin.close();
		in.close();
		return result;
	}
	
	/**
	 * 带压缩的序列化
	 * @param obj
	 * @param out
	 * @throws Exception
	 */
	public static void serializeZip(Object obj, OutputStream out) throws Exception {
		byte[] data = serialize(obj);
		InputStream in = new ByteArrayInputStream(data);
		//在zip关闭流，流一律在方法栈底进行关闭
		zip(in, out);
	}
	
	/**
	 * 从文件中解压缩成对象，
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Object unserializeZip(File file) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		unzip(file, outputStream);
		
		ByteArrayInputStream btin = new ByteArrayInputStream(outputStream.toByteArray());
		
		return unserialize(btin);
	}
	
	/**
	 * 加密Object序列化 
	 * @param obj
	 * @param out
	 * @throws Exception
	 */
	public static void serializeDes(Object obj, OutputStream out) throws Exception {
		byte[] data = serialize(obj);
		DesUtil des = new DesUtil();
		data = des.desEncrypt(data);
		out.write(data);
		out.close();
	}
	
	/**
	 * 加密序列化并返回byte数组
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] serialzeDes(Object obj) throws Exception {
		ByteArrayOutputStream btout = new ByteArrayOutputStream();
		serializeDes(obj, btout);
		return btout.toByteArray();
	}
	
	/**
	 * DES加密、压缩对象序列化
	 * @param obj
	 * @param out
	 * @throws Exception
	 */
	public static void serializeZipDes(Object obj, OutputStream out) throws Exception {
		byte[] dataDES = serialzeDes(obj); //加密后的字节数组
		ByteArrayInputStream btin = new ByteArrayInputStream(dataDES);
		zip(btin, out);
	}

}
