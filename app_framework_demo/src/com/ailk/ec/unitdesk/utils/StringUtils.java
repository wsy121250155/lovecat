package com.ailk.ec.unitdesk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************************************************
 * class name:StringOperate
 * 
 * description：字符串处理器，封装了处理字符串的常用方法。如：分割、查找等方法
 * 
 * 
 * time：07.2.15
 ******************************************************************************/
public class StringUtils
{

	/***************************************************************************
	 * 函数功能：以指定的标记对字符串进行分割,最后将分割的各部分拼接成字符串
	 * 
	 * 参数说明：
	 * 
	 * @str:待分割的字符串
	 * 
	 * @mark:指定的标记
	 * 
	 *             返回值：拼接成的字符串
	 **************************************************************************/
	public static String splitToStr(String str, String mark)
	{
		StringTokenizer st = new StringTokenizer(str, mark);
		String returnText = "";// 要返回的分割后的字符串
		while (st.hasMoreElements())
		{
			String token = st.nextToken().trim();
			returnText += token.trim();
		}
		return returnText;
	}

	/***************************************************************************
	 * 函数功能：以特定的分割符，将字符串进行分割，将分割的各个部份存放入list对象中
	 * 
	 * 参数说明：
	 * 
	 * @str:待分割的字符串
	 * 
	 * @mark:指定的分割符
	 * 
	 *              返回值：返回存放着分割好的字符串各个部门的列表对象
	 **************************************************************************/
	public static List<String> splitToList(String str, String mark)
	{
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, mark);
		while (st.hasMoreElements())
		{
			String token = st.nextToken();
			list.add(token);
		}
		return list;
	}

	/***************************************************************************
	 * 函数功能：判断指定的字符父串中是否存在指定的子串
	 * 
	 * 参数说明：
	 * 
	 * @fstr:指定的字符父串
	 * @cstr:指定的字符子串
	 * 
	 *               返回值：存在返回true;不存在或者父串或子串为null，返回false;
	 **************************************************************************/
	public static boolean isContainChild(String fstr, String cstr)
	{
		boolean flag = false;
		try
		{
			if (fstr == null || cstr == null)
				return false;
			else
			{
				if (fstr.indexOf(cstr) == -1)
					flag = false;
				else
					flag = true;
			}
		} catch (Exception ex)
		{
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * 将指定的list对象中的元素，转换成字符串。每个元素之间用mark标记分隔开
	 * 
	 * @param:list 要转换的list对象
	 * @param:mark 元素之间的分隔符号
	 * @return:返回转换后的字符串
	 * @throws:
	 */
	public static String convertListToString(List<String> list, String mark)
	{
		try
		{
			int size = list.size();
			StringBuffer returnText = new StringBuffer("");
			for (int i = 0; i < size; i++)
			{
				String str = list.get(i);
				if (str == null)
					continue;
				if (i != size - 1)
					returnText.append(str.toCharArray()).append(mark);
				else
					returnText.append(str.toCharArray());
			}
			return returnText.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/***************************************************************************
	 * 函数功能：判断指定字符串是否是数字
	 * 
	 * 参数说明：
	 * 
	 * @str:待判断的字符串
	 * 
	 *              返回值：是返回true;否则返回false;
	 **************************************************************************/
	public static boolean isNumber(String str)
	{
		boolean flag = false;
		try
		{
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches())
				flag = false;
			else
				flag = true;
		} catch (Exception ex)
		{
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/***************************************************************************
	 * 函数功能：全角转半角
	 * 
	 * 参数说明：
	 * 
	 * @QJstr:全角字符串
	 * 
	 *              返回值：返回转换后的半角字符串
	 **************************************************************************/
	public static String q2b(String QJstr)
	{
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++)
		{
			try
			{
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}

			if (b[3] == -1)
			{
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;

				try
				{
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

	/***************************************************************************
	 * 函数功能：半角转全角
	 * 
	 * 参数说明：
	 * 
	 * @QJstr:半角字符串
	 * 
	 *              返回值：返回转换后的全角字符串
	 **************************************************************************/
	public static String b2q(String BJstr)
	{
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < BJstr.length(); i++)
		{
			try
			{
				Tstr = BJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			if (b[3] != -1)
			{
				b[2] = (byte) (b[2] - 32);
				b[3] = -1;
				try
				{
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

	/***************************************************************************
	 * 函数功能：判断指定的字符串是否是中文
	 * 
	 * 参数说明：
	 * 
	 * @QJstr:待判断的字符串
	 * 
	 *                返回值：是中文返回true;否则返回false;
	 **************************************************************************/
	public static boolean isCharacter(String str)
	{
		try
		{
			return str.matches("[\\u4E00-\\u9FA5]+");
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	// md5
	private final static String[] hexDigits =
	{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 将指定的字符串进行MD5加密
	 * 
	 * @param:origin指定字符串
	 * @return:
	 * @throws:
	 */
	public static String MD5Encode(String origin)
	{
		String resultString = null;
		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 将null对象转换成空串""，否则返回对象本身
	 * 
	 * @param:obj 需要转换的对象
	 * @return:返回转换后的对象
	 * @throws:
	 */
	public static Object nullToEmp(Object obj)
	{
		if (obj == null)
			return "";
		else
			return obj;
	}

	public static boolean isEmpty(String str)
	{
		if (str == null || str.trim().equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 按字符长度截取字符，将字符串转为utf-8编码，即都为两个字节
	 * 
	 * @param s
	 *            原字符串
	 * @param length
	 *            截取长度
	 * @return 截取后的长度
	 */
	public static String subChineseString(String s, int length)
	{
		String str = "";
		byte[] bytes;
		try
		{
			bytes = s.getBytes("Unicode");

			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始
			for (; i < bytes.length && n < length; i++)
			{
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1)
				{
					n++; // 在UCS2第二个字节时n加1
				} else
				{
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0)
					{
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1)
			{
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			str = new String(bytes, 0, i, "Unicode");
			return str;
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			return str;
		}
	}

	/**
	 * 截取字符串添加省略号
	 * 
	 * @param str
	 *            带处理字符串
	 * @param position
	 *            截取位置,汉字、数字、字母都算一个位置
	 * @return 带省略号的字符串
	 */
	public static String subStringAddEllipsis(String str, int position)
	{
		String result = "";
		// 因汉字和字符都处理为两个字节，故截取位置需X2
		int subPostion = position * 2;
		if (str != null && !str.equals(""))
		{
			try
			{
				if (str.getBytes("Unicode").length > subPostion)
				{
					str = StringUtils.subChineseString(str, subPostion) + "...";
					// content = content.substring(0, 12) + "...";
				}
				result = str;
			} catch (UnsupportedEncodingException e)
			{
				return "";
			}
		}
		return result;
	}

	/**
	 * 拆分字符串
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param separatorChars
	 *            用来拆分的分割字符
	 * @param max
	 *            要拆分字符串的最大长度
	 * @param preserveAllTokens
	 * @return 拆分后的字符串
	 */
	private static String[] splitWorker(String str, String separatorChars,
			int max, boolean preserveAllTokens)
	{
		if (str == null)
		{
			return null;
		}
		int len = str.length();
		if (len == 0)
		{
			return new String[]
			{ "" };
		}
		Vector<String> vector = new Vector<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null)
		{
			while (i < len)
			{
				if (str.charAt(i) == '\r' || str.charAt(i) == '\n'
						|| str.charAt(i) == '\t')
				{
					if (match || preserveAllTokens)
					{
						lastMatch = true;
						if (sizePlus1++ == max)
						{
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					start = ++i;
				} else
				{
					lastMatch = false;
					match = true;
					i++;
				}
			}
		} else if (separatorChars.length() == 1)
		{
			char sep = separatorChars.charAt(0);
			while (i < len)
			{
				if (str.charAt(i) == sep)
				{
					if (match || preserveAllTokens)
					{
						lastMatch = true;
						if (sizePlus1++ == max)
						{
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					start = ++i;
				} else
				{
					lastMatch = false;
					match = true;
					i++;
				}
			}
		} else
		{
			while (i < len)
			{
				int id = i + separatorChars.length() < len ? i
						+ separatorChars.length() : len;
				if (separatorChars.indexOf(str.charAt(i)) >= 0
						&& separatorChars.equals(str.substring(i, id)))
				{
					if (match || preserveAllTokens)
					{
						lastMatch = true;
						if (sizePlus1++ == max)
						{
							i = len;
							lastMatch = false;
						}
						vector.addElement(str.substring(start, i));
						match = false;
					}
					i += separatorChars.length();
					start = i;
				} else
				{
					lastMatch = false;
					match = true;
					i++;
				}
			}
		}

		if (match || preserveAllTokens && lastMatch)
		{
			vector.addElement(str.substring(start, i));
		}
		String[] ret = new String[vector.size()];
		vector.copyInto(ret);
		return ret;
	}

	/**
	 * 将字符串str按子字符串separatorChars 分割成数组
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param separatorChars
	 *            用来拆分的分割字符
	 * @return 拆分后的字符串
	 */
	public static String[] split2(String str, String separatorChars)
	{
		return splitWorker(str, separatorChars, -1, false);
	}

    /**
     * 转换double数据格式
     * @param _doubleVal
     * @return double
     */
    public static double formatDoubleValue(double _doubleVal){
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        return Double.parseDouble(decimalFormat.format(_doubleVal));
    }

}
