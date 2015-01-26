package com.ailk.ec.unitdesk.utils.coord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;

import com.ailk.ec.unitdesk.utils.Base64;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * 
 * 
 */

public class Gcj02ToBD {
	public static void main(String args[]) throws ParseException {
		// changgeXY("116.397428", "39.90923");
		changgeXY("118.784256", "32.07869");
	}

	public static HashMap<String, String> changgeXY(String xx, String yy) {
		HashMap<String, String> localHashMap = new HashMap<String, String>();
		try {
			Socket s = new Socket("api.map.baidu.com", 80);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					s.getInputStream(), "UTF-8"));
			OutputStream out = s.getOutputStream();
			StringBuffer sb = new StringBuffer(
					"GET /ag/coord/convert?from=0&to=4");
			sb.append("&x=" + xx + "&y=" + yy);
			sb.append("&callback=BMap.Convertor.cbk_3976 HTTP/1.1\r\n");
			sb.append("User-Agent: Java/1.6.0_20\r\n");
			sb.append("Host: api.map.baidu.com:80\r\n");
			sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
			sb.append("Connection: Close\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes());
			String json = "";
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				json += tmp;
			}

			int start = json.indexOf("cbk_3976");
			int end = json.lastIndexOf("}");
			if (start != -1 && end != -1 && json.contains("\"x\":\"")) {
				json = json.substring(start, end);
				String[] point = json.split(",");
				String x = point[1].split(":")[1].replace("\"", "");
				String y = point[2].split(":")[1].replace("\"", "");
				localHashMap.put("lon", new String(decode(x)));
				localHashMap.put("lat", new String(decode(y)));
				return localHashMap;
			} else {
				Log.d("gps坐标无效！！");
			}
			out.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
			com.ailk.ec.unitdesk.utils.Log.saveErrorLogToServer(e);
		}
		return null;

	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {

		byte[] bt = null;

		try {
			Base64 decoder = new Base64();
			bt = decoder.decode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bt;
	}
}
