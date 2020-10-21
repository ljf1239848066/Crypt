package com.lxzh123.crypt;

import android.util.Log;

/**
 * Log日志类
 *
 * @author geetest lxzh
 * @date 2020/8/28
 */
public class LogUtils extends AbsLog{
    private static int LEVEL = VERBOSE;
    private static String TAG = Constants.LOG_TAG;

    public static void init(int level, String tag) {
        LEVEL = level;
        TAG = tag;
        Log.i(TAG, "init log level: " + level);
        PrintLog.i("init log level: " + level);
    }

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, msg);
        }
        PrintLog.v(msg);
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, msg);
        }
        PrintLog.d(msg);
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.i(TAG, msg);
        } else {
        }
        PrintLog.i(msg);
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.w(TAG, msg);
        }
        PrintLog.w(msg);
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        } else {
        }
        PrintLog.e(msg);
    }

    public static void fe(String msg) {
        Log.e(TAG, msg);
        PrintLog.fe(msg);
    }

    public static void v(String msg, Throwable t) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, msg, t);
        }
        PrintLog.v(msg, t);
    }

    public static void d(String msg, Throwable t) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, msg, t);
        }
        PrintLog.d(msg, t);
    }

    public static void i(String msg, Throwable t) {
        if (LEVEL <= INFO) {
            Log.i(TAG, msg, t);
        } else {
        }
        PrintLog.i(msg, t);
    }

    public static void w(String msg, Throwable t) {
        if (LEVEL <= WARN) {
            Log.w(TAG, msg, t);
        }
        PrintLog.w(msg, t);
    }

    public static void e(String msg, Throwable t) {
        PrintLog.e(msg, t);
    }

    public static void fe(String msg, Throwable t) {
        Log.e(TAG, msg, t);
        PrintLog.fe(msg, t);
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
        PrintLog.v(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
        PrintLog.d(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
        PrintLog.i(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
        PrintLog.w(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
        PrintLog.e(TAG, msg);
    }

    public static void fe(String tag, String msg) {
        Log.e(tag, msg);
        PrintLog.fe(TAG, msg);
    }
}
