package com.lxzh123.crypt;

/**
 * System.out.print Log 日志类
 * 在分析线上应用的时候，有些应用会发布时通过混淆配置清楚应用内所有对 android.util.Log 的引用，导致线上应用无法查看日志
 * 但该混淆方法不会移除 System.out.print/println 打印的日志，可以作为保留日志功能，以在特殊情况下强制打印日志进行分析
 *
 * @author geetest lxzh
 * @date 2020/8/28
 */
public class PrintLog extends AbsLog{
    private static int LEVEL = VERBOSE;
    private static final String DEFAULT_TAG = Constants.LOG_TAG;
    private static String TAG = "";
//    private static final byte[] SYSTEM_CLASS_NAME = new byte[]{0x6a, 0x61, 0x76, 0x61, 0x2e, 0x6c, 0x61, 0x6e, 0x67, 0x2e, 0x53, 0x79, 0x73, 0x74, 0x65, 0x6D};// "java.lang.System"
//    private static final byte[] OUT_FIELD_NAME = new byte[]{0x6F, 0x75, 0x74};                               // "out"
//    private static final byte[] PRINTLN_METHOD_NAME = new byte[]{0x70, 0x72, 0x69, 0x6E, 0x74, 0x6C, 0x6E};  // "println"

    private static boolean isEnable = false;
    private static Object SYSTEM_OUT;
    private static java.lang.reflect.Method PRINTLN_METHOD;

    public static void init(int level) {
        LEVEL = level;
    }

    public static boolean getState() {
        return isEnable;
    }

//    public static void openLog(String methodName) { // println
//        openLog(methodName, DEFAULT_TAG);
//    }
//
//    public static void openLog(String methodName, String tag) { // println
//        Class<?> systemClass = null;
//        try {
//            systemClass = Class.forName(new String(SYSTEM_CLASS_NAME));
//            java.lang.reflect.Field systemOutField = systemClass.getDeclaredField(new String(OUT_FIELD_NAME));
//            SYSTEM_OUT = systemOutField.get(systemClass);
//            PRINTLN_METHOD = SYSTEM_OUT.getClass().getMethod(methodName, String.class);
//            isEnable = true;
//            TAG = tag;
//        } catch (Exception e) {
//
//        }
//    }

    public static void openLog(String[] args) { // println
        Class<?> systemClass;
        try {
            systemClass = Class.forName(args[0]);//"java.lang.System"
            java.lang.reflect.Field systemOutField = systemClass.getDeclaredField(args[1]);//out
            SYSTEM_OUT = systemOutField.get(systemClass);
            PRINTLN_METHOD = SYSTEM_OUT.getClass().getMethod(args[2], String.class);//println
            isEnable = true;
            if (args.length == 4) {
                TAG = args[3];
            }
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
    }

    public static Object get() {
        return isEnable && SYSTEM_OUT != null && PRINTLN_METHOD != null;
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void fe(String msg) {
        fe(TAG, msg);
    }

    public static void v(String msg, Throwable t) {
        v(TAG, msg, t);
    }

    public static void d(String msg, Throwable t) {
        d(TAG, msg, t);
    }

    public static void i(String msg, Throwable t) {
        i(TAG, msg, t);
    }

    public static void w(String msg, Throwable t) {
        w(TAG, msg, t);
    }

    public static void e(String msg, Throwable t) {
        e(TAG, msg, t);
    }

    public static void fe(String msg, Throwable t) {
        fe(TAG, msg, t);
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            log(V, tag, msg, null);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            log(D, tag, msg, null);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            log(I, tag, msg, null);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            log(W, tag, msg, null);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            log(E, tag, msg, null);
        }
    }

    public static void fe(String tag, String msg) {
        log(E, tag, msg, null);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (LEVEL <= VERBOSE) {
            log(V, tag, msg, t);
        }
    }

    public static void d(String tag, String msg, Throwable t) {
        if (LEVEL <= DEBUG) {
            log(D, tag, msg, t);
        }
    }

    public static void i(String tag, String msg, Throwable t) {
        if (LEVEL <= INFO) {
            log(I, tag, msg, t);
        }
    }

    public static void w(String tag, String msg, Throwable t) {
        if (LEVEL <= WARN) {
            log(W, tag, msg, t);
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (LEVEL <= ERROR) {
            log(E, tag, msg, t);
        }
    }

    public static void fe(String tag, String msg, Throwable t) {
        log(E, tag, msg, t);
    }

    public static void log(String level, String tag, String msg, Throwable t) {
        if (!isEnable || SYSTEM_OUT == null || PRINTLN_METHOD == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tag);
        sb.append(" [");
        sb.append(level);
        sb.append("] ");
        sb.append(msg);
        if (t != null) {
            sb.append("\n");
            sb.append(getStackTraceString(t));
        }
//        System.out.println(sb.toString());
        try {
            PRINTLN_METHOD.invoke(SYSTEM_OUT, sb.toString());
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
    }
}
