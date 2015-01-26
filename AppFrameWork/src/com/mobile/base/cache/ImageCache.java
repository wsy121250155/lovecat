package com.mobile.base.cache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import com.mobile.base.commons.verify.MD5;
import com.mobile.base.util.Frame;

/**
 * 使用md5及宽高来唯一标识图片，并以相应字符串来锁定文件I/O操作
 * 
 * @author yjh
 *
 */
public class ImageCache {
	private static Set<String> removeMap;
	private static String IMG_PATH = "image";

	/**
	 * 保存图片
	 * 
	 * @param url
	 * @param data
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public void save(String url, byte[] data, float w, float h)
			throws Exception {
		FileOutputStream fps = null;
		try {
			String filename = getFilename(url, w, h);

			// 同步文件读写
			synchronized (filename) {
				File file = CacheUtil
						.getPath(Frame.CONTEXT, IMG_PATH, filename);
				if (file != null) {
					fps = new FileOutputStream(file);
					fps.write(data);
					fps.flush();
				}
			}
		} finally {
			if (fps != null) {
				fps.close();
			}
		}

	}

	public byte[] read(String url, float w, float h) {
		byte[] data = null;

		return data;
	}

	/**
	 * 获取对应文件
	 * 
	 * @param url
	 * @param w
	 * @param h
	 * @return
	 * @throws IOException
	 */
	public File check(String url, float w, float h) throws IOException {
		File file = null;
		String filename = getFilename(url, w, h);
		synchronized (filename) {
			File dir = CacheUtil.getDPath(Frame.CONTEXT, IMG_PATH);
			if ((dir != null) && dir.isDirectory()) {
				file = CacheUtil.getPath(Frame.CONTEXT, IMG_PATH, filename);

			}
		}

		return file;
	}

	public String getFilename(String url, float w, float h) {
		StringBuilder sb = new StringBuilder();

		sb.append(MD5.md5(url)).append("#").append(w).append("x").append(h);

		return sb.toString();
	}

}