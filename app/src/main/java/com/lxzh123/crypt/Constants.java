package com.lxzh123.crypt;

/**
 * 全局常量工具类
 *
 * @author geetest lxzh
 * @date 2020/8/28
 */
public class Constants {
    /**
     * log日志的TAG
     */
    public static final String LOG_TAG = Strings.TAG();

    /**
     * SDK的版本号
     */
    public static final String SDK_VERSION = Strings.Version();

    /**
     * BASE url
     */
    public static final String API_SERVER = "https://themis.geetest.com";

    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String FILE = "file:///";
    public static final String STORAGE = "/storage";
    public static final String SDCARD = "/sdcard";

    /**
     * 无网络连接
     */
    public final static int NETTYPE_NONE = 0x0;
    /**
     * Wifi网络
     */
    public final static int NETTYPE_WIFI = 0x01;
    /**
     * WAP连接
     */
    public final static int NETTYPE_CMWAP = 0x02;
    /**
     * NET网络
     */
    public final static int NETTYPE_CMNET = 0x03;

    /**
     * 校验 API
     */
    public static final String GET_WORDS = "/v1/get_words";

    /**
     * 上传 API
     */
    public static final String REPORT = "/v1/report_res";

    /**
     * 最小超时时间
     */
    public static final int MIN_TIME_OUT = 1000;
    /**
     * 最大超时时间
     */
    public static final int MAX_TIME_OUT = 15 * 1000;
    /**
     * 默认的超时时间
     */
    public static final int DEFAULT_TIME_OUT = 5 * 1000;

    /**
     * SDK 与服务端对接版本号
     */
    public static final String Version = "V1";
    /**
     * 平台类型 Android
     */
    public static final String ANDROID = "1";

    /**
     * 异步搜索结果回调
     */
    public static final int MSG_ASYNC_OUTPUT_ON_RESULT = 100;

    /**
     * 敏感词字典
     */
//    public static final String SENSITIVE_WORDS_FILE_NAME = "SensitiveWords.csv";
    public static final String SENSITIVE_WORDS_FILE_NAME = Strings.SensitiveWords();//"SensitiveWords.csv";

    /**
     * 敏感词正则表达式
     */
    public static final String SENSITIVE_REGULAR_FILE_NAME = Strings.SensitiveRegs();//"SensitiveRegs.txt";

    /**
     * Unicode
     */
    public static final String UNICODE_CATEGORY_MAP_FILE_NAME = Strings.CatType();//"CatType.data";

    /**
     * Password
     */
    public static final String GT_ZIP_PASSWORD = Strings.password();//"geetest123456"  Geetest_Themis_RES

}
