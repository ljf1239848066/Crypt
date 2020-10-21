package com.lxzh123.crypt;


import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 *
 * @author geetest 谷闹年
 * @date 2019/3/13
 */
public class StringUtils {
    /**
     * 将byte数组转换为十六进制文本
     */
    public static String toHex(byte[] buf) {
        if (buf == null || buf.length == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            out.append(HEX[(buf[i] >> 4) & 0x0f]).append(HEX[buf[i] & 0x0f]);
        }
        return out.toString();
    }

    /**
     * 将十六进制文本转换为byte数组
     */
    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        }
        char[] hex = str.toCharArray();
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    private final static char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static byte[] getBytes(String data){
        String encoding = "UTF-8";
        byte [] bytes = new byte[]{};
        try {
            bytes = data.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  bytes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        StringBuffer des = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    public static String bytesToHexString(byte[] array) {
        StringBuffer buffer = new StringBuffer();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            buffer.append(String.format("%2x", array[i]));
        }
        return buffer.toString();
    }
}
