package com.ailk.ec.unitdesk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

public class EntityUtil {
    public static final int BUFFER_LEN = 4096;
    private static char[] cacheByteBuffer = new char[4096];
    private static boolean cacheByteBufferUsed = false;

    public static Object getObject(InputStream inputStream)
            throws ClassNotFoundException, IOException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            Object obj = ois.readObject();
            return obj;
        }
        finally {
            if (ois != null)
                ois.close();
            if (inputStream != null)
                inputStream.close();
        }
    }

    public static String getString(InputStream inputStream, String charset)
            throws IOException {
        BufferedReader in = null;

        char[] buffer = getCachedByteBuffer();
        try {
            in = new BufferedReader(new InputStreamReader(inputStream, charset));
            StringBuilder sb = new StringBuilder();

            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                sb.append(buffer, 0, len);
            }
            return sb.toString();
        }
        finally {
            if (in != null)
                in.close();
            if (inputStream != null) {
                inputStream.close();
            }
            releaseCachedByteBuffer(buffer);
        }
    }

    private static synchronized char[] getCachedByteBuffer() {
        synchronized (cacheByteBuffer) {
            if (!(cacheByteBufferUsed)) {
                cacheByteBufferUsed = true;
                return cacheByteBuffer;
            }
        }

        return new char[4096];
    }

    private static void releaseCachedByteBuffer(char[] buffer) {
        synchronized (cacheByteBuffer) {
            if (buffer == cacheByteBuffer)
                cacheByteBufferUsed = false;
        }
    }
}