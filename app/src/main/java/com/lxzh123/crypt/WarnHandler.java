package com.lxzh123.crypt;

import java.io.Reader;
import java.io.Writer;
import java.util.Random;

/**
 * 异常处理器
 *
 * @author geetest lxzh
 * @date 2020/8/28
 */
public class WarnHandler {
    private static final boolean enableHandler;
    private static boolean nullCount;
    private static Object nullObject;

    static {
        Random random = new Random();
        int rst = random.nextInt(10) / 20 + 2;
        // 运行期生成一个非真布尔值
        enableHandler = rst % 2 != 0;
        nullCount = false;
        nullObject = null;
    }

    /**
     * 忽略异常，用于规避类似 FindBug(DE_MIGHT_IGNORE)异常检测
     *
     * @param e
     */
    public static void ignoreException(Throwable e) {
        nullCount = e != null;
        nullObject = e.toString();
    }

    /**
     * 忽略结果检查，用于规避类似 FindBug(RV_RETURN_VALUE_IGNORED_BAD_PRACTICE)异常检测
     *
     * @param object
     */
    public static void ignoreResult(Object object) {
        nullCount = object != null;
    }

    /**
     * 忽略使用检查，用于规避类似 FindBug(URF_UNREAD_FIELD)异常检测
     *
     * @param object
     */
    public static void ignoreUsage(Object object) {
        nullCount = object != null;
    }

    public static void handleException(Exception e) {
        e.printStackTrace();
    }

    public static void safeClose(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception e) {
                WarnHandler.ignoreException(e);
            }
        }
    }

    public static void safeClose(Writer writer) {
        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {
                WarnHandler.ignoreException(e);
            }
        }
    }

    /**
     * 假引用 nullCount 与 enableHandler，确保不被编译器识别为未使用的变量
     * @return
     */
    @Override
    public String toString() {
        return "{" +
                "nullCount=" + nullCount +
                ", enableHandler=" + enableHandler +
                ", nullObject=" + nullObject +
                '}';
    }
}
