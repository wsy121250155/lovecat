package com.ailk.ec.unitdesk.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Noah
 * Date: 13-3-12
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
public class FileDownloadInfo {
    /**
     * 正在下载数据
     */
    public static final int STATE_LOADING = 0;
    /**
     * 数据全部下载完成
     */
    public static final int STATE_COMPLETED = 1;
    /**
     * 数据下载过程中被暂停
     */
    public static final int STATE_INTERRUPTED = 2;
    /**
     * 下载安装包失败
     */
    public static final int STATE_DOWNLOAD_FAIL = 3;
    /**
     * 刷新频率
     */
    public static final int ACTION_REFRESH = 1;

    /**
     * 下载资源名称
     */
    public String name;
    /**
     * 下载链接
     */
    public String url;
    /**
     * 资源字节数
     */
    public long size;
    /**
     * 已经下载的字节数
     */
    public long loadedSize;
    /**
     * 下载状态
     */
    public int state;
    /**
     * 标记是否允许下载的boolean变量
     */
    public boolean enable;
    /**
     * 文件存放路径
     */
    public String filePath;
}
