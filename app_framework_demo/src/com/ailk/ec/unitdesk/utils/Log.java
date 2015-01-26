package com.ailk.ec.unitdesk.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.Constants;
import com.ailk.ec.unitdesk.net.ApiClient;

/* =========================================================
 * @CreateTime 2012-03-19 10:27
 * @Description: 日志封装类
 * @version V1.0
 * =========================================================
 */
public class Log {

	private static final String TAG = "Unidesk";

	// 系统日志的控制开关
	public static final boolean DEBUG = true;

	public static void v(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.v(tag, msg);
		}
	}

	public static void i(String msg) {
		if (DEBUG) {
			android.util.Log.i(TAG, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.i(tag, msg);
		}
	}

	public static void d(String msg) {
		if (DEBUG) {
			android.util.Log.d(TAG, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.d(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.w(tag, msg, tr);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			android.util.Log.e(TAG, msg);
		}
	}

	public static void e(String msg, Throwable e) {
		if (DEBUG) {
			android.util.Log.e(TAG, msg, e);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable e) {
		if (DEBUG) {
			android.util.Log.e(tag, msg, e);
		}
	}

	public static void trace(String tag, String msg) {
		if (DEBUG) {
			android.util.Log.i(tag, msg);
		}
	}

	/**
	 * Building Message
	 * 
	 * @param msg
	 *            The message you would like logged.
	 * @return Message String
	 */
	// protected static String buildMessage(String remark, String msg) {
	// // StackTraceElement caller = new
	// // Throwable().fillInStackTrace().getStackTrace()[2];
	// //
	// // return new StringBuilder()
	// // .append(caller.getClassName())
	// // .append(".")
	// // .append(caller.getMethodName())
	// // .append("(): ")
	// // .append(msg).toString();
	// return remark + ": " + msg;
	// }

	/**
	 * 开始一个时间日志，返回当前的tickcount
	 * 
	 * @return 当前时间的毫秒数
	 */
	// public static long startTime() {
	// return System.currentTimeMillis();
	// }

	/**
	 * 统计耗时时间
	 * 
	 * @param start
	 *            日志开始时间
	 * @param msg
	 *            日志内容
	 */
	// public static void countTimeLog(long start, String msg) {
	// long end = System.currentTimeMillis();
	// if (DEBUG) {
	// android.util.Log.d(TAG, msg + " count time：" + (end - start)
	// + " ms");
	// }
	// }

	public static void e(String msg, boolean isSave) {

		if (DEBUG) {
			if (isSave) {
				File errorLog = new File(Constants.LOG_DIR + "error.log");
				if (!errorLog.exists()) {
					File dir = new File(Constants.LOG_DIR);
					if (!dir.exists()) {
						dir.mkdirs();
					}
				}

				int length = 0;

				char[] buffer = new char[2048];

				BufferedReader br = null;
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter(errorLog, true));
					br = new BufferedReader(new StringReader(msg + "\n"));

					while ((length = br.read(buffer)) != -1) {
						bw.write(buffer, 0, length);
					}
					bw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {

					try {
						if (bw != null) {
							bw.close();
						}
						if (br != null) {
							br.close();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				e(msg);
			}
		}

	}

	/**
	 * 保存错误日志至服务器
	 * 
	 * @param error
	 */
	public static void saveErrorLogToServer(Throwable error) {
		try {
			StringBuffer stackTrace = new StringBuffer(
					error.getLocalizedMessage() + " : [ ");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			error.printStackTrace(pw);
			stackTrace.append(sw.toString());
			stackTrace.append(" ]");

//			ApiClient.errorLog(null, 999, android.os.Build.MODEL,
//					android.os.Build.VERSION.RELEASE, ToolUtil.getDeviceId(),
//					"", stackTrace.toString(), CommonApplication.getInstance()
//							.getPackageName(), ToolUtil.getDateTimeStr(),
//					CommonApplication.getInstance().staffId,
//					CommonApplication.getInstance().versionCode,
//					CommonApplication.getInstance().versionName,
//					android.os.Build.MODEL, CommonApplication.getInstance()
//							.getString(R.string.UNIDESK_ACTION_ERROR_LOG));
		} catch (Exception ex) {
		}
	}

}
