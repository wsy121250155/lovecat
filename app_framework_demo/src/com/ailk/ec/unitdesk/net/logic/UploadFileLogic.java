package com.ailk.ec.unitdesk.net.logic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.os.Handler;
import android.os.Message;

import com.ailk.ec.unitdesk.utils.Log;

/**
 * 文件上传
 * 
 * @author spoon
 * 
 */
public class UploadFileLogic {

	private static UploadFileLogic instance;

	public synchronized static UploadFileLogic getInstance() {
		if (instance == null) {
			instance = new UploadFileLogic();
		}
		return instance;
	}

	private UploadFileLogic() {

	}

	/**
	 * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
	 * 
	 * @param actionUrl
	 * @param params
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public static String post(Handler handler, int what, String httpUrl,
			Map<String, String> params, Map<String, File> files)
			throws IOException {

		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL uri = new URL(httpUrl);
		Log.d("UploadFileLogic", "httpUrl : " + httpUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(30 * 1000); // 缓存的最长时间
		conn.setConnectTimeout(30 * 1000);
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
				+ ";boundary=" + BOUNDARY);
		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\""
					+ entry.getKey() + "\"" + LINEND);
			sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
			sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			sb.append(LINEND);
		}

		DataOutputStream outStream = new DataOutputStream(
				conn.getOutputStream());
		Log.d("UploadFileLogic", "begin to upload...");
		outStream.write(sb.toString().getBytes());
		// 发送文件数据
		if (files != null)
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ file.getKey() + "\"" + LINEND);
				sb1.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());

				InputStream is = new FileInputStream(file.getValue());
				byte[] buffer = new byte[1024];
				int len = 0;
				int totalLenth = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
					totalLenth += len;
					Log.d("UploadFileLogic", "upload file length: "
							+ totalLenth);
				}

				is.close();
				outStream.write(LINEND.getBytes());
			}

		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		Log.d("UploadFileLogic", "end to upload...");
		// 得到响应码
		int res = conn.getResponseCode();
		Log.d("UploadFileLogic", "response code is " + res);
		if (res == 200) {
			InputStream in = conn.getInputStream();
			int ch;
			StringBuilder sb2 = new StringBuilder();
			while ((ch = in.read()) != -1) {
				sb2.append((char) ch);
			}
			Log.d("UploadFileLogic", sb2.toString());
			Message msg = Message.obtain();
			msg.what = what;
			msg.obj = sb2.toString();
			handler.sendMessage(msg);
		} else {
			Message msg = Message.obtain();
			msg.what = 0;
			msg.obj = res;
			handler.sendMessage(msg);
		}
		outStream.close();
		conn.disconnect();
		return "";
	}

}
