package com.lxzh123.crypt;

/**
 * description $
 * author      Created by lxzh
 * date        2020/10/21
 */
public class Native {
    static {
        System.loadLibrary("crypt");
    }

    public static native void init(String value, String password);
}
