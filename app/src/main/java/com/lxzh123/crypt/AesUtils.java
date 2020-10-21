package com.lxzh123.crypt;

import android.text.TextUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES工具类
 */
public class AesUtils {

    private static final byte[] IV = "0000000000000000".getBytes();

    private static final char[] SEC_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();


    public static byte[] encrypt1(byte[] content, String password) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);// 初始化为加密模式的密码器
            return cipher.doFinal(content);// 加密
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
        return null;
    }

    public static byte[] decrypt1(byte[] byteContent, String password) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);// 初始化为解密模式的密码器
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
        return null;
    }

    /**
     * AES加密字符串
     *
     * @param content  需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     */
    public static String encryptNew(String content, String password) {
        byte[] bytes = encrypt(content, password);
        if (bytes != null) {
            return StringUtils.toHex(bytes);
        } else {
            return null;
        }
    }

    /**
     * AES加密字符串
     *
     * @param content  需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String password) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(password)) {
            LogUtils.e("invalid input param");
            return null;
        }
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);// 初始化为加密模式的密码器
            return cipher.doFinal(byteContent);// 加密
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
        return null;
    }

    /**
     * 自定义的AES解密，基于 IV 指定的 IvParameterSpec
     * 适用于服务端 pre_get_token 与 pre_gateway 请求返回数据的解密
     * 解密AES加密过的字符串
     *
     * @param content  AES加密过的内容
     * @param password 加密时的密码
     * @return 明文
     */
    public static String decryptGt(String content, String password) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);// 初始化为解密模式的密码器
            // 服务端 pre_get_token 与 pre_gateway 请求返回数据用 StringUtils.hexToBytes 转换为 byte
            // 数组，默认的 getBytes("utf-8") 不匹配会导致解密失败
            byte[] byteContent = StringUtils.hexToBytes(content);
            // 本地 riskinfo 请求加密的数据解密时，入参用 getBytes("utf-8") 转换可正常解密
//            byte[] byteContent = content.getBytes("utf-8");
            byte[] result = cipher.doFinal(byteContent);
            return new String(result); // 明文
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
        return null;
    }

    /**
     * 标准AES解密，基于 IV 指定的 IvParameterSpec
     * 解密AES加密过的字符串
     *
     * @param content  AES加密过的内容
     * @param password 加密时的密码
     * @return 明文
     */
    public static String decrypt(String content, String password) {
        try {
            return decrypt(content.getBytes("UTF-8"), password);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 标准AES解密，基于 IV 指定的 IvParameterSpec
     * 解密AES加密过的字符串
     *
     * @param byteContent  AES加密过的内容
     * @param bytePassword 加密时的密码
     * @return 明文
     */
    public static String decrypt(byte[] byteContent, String bytePassword) {
        return decrypt(byteContent, bytePassword);
    }

    public static String decrypt(byte[] byteContent, byte[] password) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec secretKey = new SecretKeySpec(password, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(byteContent);
            return new String(result); // 明文
        } catch (Exception e) {
            WarnHandler.ignoreException(e);
        }
        return null;
    }
}