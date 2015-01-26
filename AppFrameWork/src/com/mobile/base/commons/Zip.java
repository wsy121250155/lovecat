package com.mobile.base.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.mobile.base.util.ConstantResource;

public class Zip {
	/**
	 * 压缩到指定文件
	 * @param in
	 * @param out
	 * @param name
	 * @throws IOException
	 */
	public static void zip(InputStream in, OutputStream out, String name) throws IOException {
		ZipOutputStream zops = new ZipOutputStream(new BufferedOutputStream(out));
		ZipEntry zpe = new ZipEntry(name);
		zops.putNextEntry(zpe);
		byte[] buff = new byte[ConstantResource.BUFF_SIZE];
		BufferedInputStream bin = new BufferedInputStream(in, ConstantResource.BUFF_SIZE);
		int len = 0;
		while((len = bin.read(buff, 0, ConstantResource.BUFF_SIZE)) != -1) {
			zops.write(buff, 0, len);
		}
		bin.close();
		in.close();
		zops.close();
	}
	
	public static void zip(InputStream in, OutputStream out) throws Exception {
		zip(in, out, "retn");
	}
	
	/**
	 * 解压缩到指定流
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void unzip(InputStream in, OutputStream out) throws IOException {
		ZipInputStream zips = new ZipInputStream(in);
		
		if(zips.getNextEntry() != null) {
			BufferedInputStream bis = new BufferedInputStream(zips);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			int len = 0;
			byte[] buff = new byte[ConstantResource.BUFF_SIZE];
			while((len = bis.read(buff, 0, ConstantResource.BUFF_SIZE)) != -1) {
				bos.write(buff, 0, len);
			}
			bis.close();
			bos.close();
		}
		zips.close();
		in.close();
		out.close();
	}
	
	public static void unzip(File file, OutputStream out) throws FileNotFoundException, IOException {
		unzip(new FileInputStream(file), out);
	}
}
