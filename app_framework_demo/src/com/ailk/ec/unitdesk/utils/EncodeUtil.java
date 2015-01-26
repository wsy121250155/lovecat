package com.ailk.ec.unitdesk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.logging.Logger;


public class EncodeUtil {
    private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final String charset = "UTF-8";

    public static String enURLcode(String s) {
        if (s == null)
            return "";
        try {
            String charencode = BaseEncode.encode(s.getBytes("UTF-8"));
            return charencode + "z___" + getMd5(charencode);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeURL(String enCode) {
        if (enCode != null) {
            String[] splitStr = enCode.split("z___");
            if ((splitStr != null) && (splitStr.length > 1))
                try {
                    String encodeUrl = splitStr[0];
                    if ((encodeUrl == null) || (!(getMd5(encodeUrl).equals(splitStr[1]))))
                        return "";
                    return new String(BaseEncode.decode(encodeUrl), "UTF-8");
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "";
                }

        }
        label64: return "";
    }

    public static String getMd5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(strTemp);
            byte[] md = messageDigest.digest();

            int k = 0;
            int j = md.length;
            char[] str = new char[j * 2];

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            String tempStr = new String(str);
            if (tempStr.length() > 8)
                return tempStr.substring(2, 5);

            return new String(str);
        }
        catch (Exception e) {
        }
        return null;
    }

    public static String escape(String s) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(s.length() * 6);
        for (int i = 0; i < s.length(); ++i) {
            char j = s.charAt(i);
            if ((Character.isDigit(j)) || (Character.isLowerCase(j)) ||
                    (Character.isUpperCase(j))) {
                tmp.append(j);
            }
            else if (j < 256) {
                tmp.append("%");
                if (j < '\16')
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            }
            else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String s) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(s.length());
        int lastPos = 0;
        int pos = 0;

        while (lastPos < s.length()) {
            pos = s.indexOf("%", lastPos);
            if (pos == lastPos) {
                char ch;
                if (s.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(s.substring(pos + 2, pos + 6),
                            16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else {
                    ch = (char) Integer.parseInt(s.substring(pos + 1, pos + 3),
                            16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else if (pos == -1) {
                tmp.append(s.substring(lastPos));
                lastPos = s.length();
            }
            else {
                tmp.append(s.substring(lastPos, pos));
                lastPos = pos;
            }
        }

        return tmp.toString();
    }
}
