package com.lxzh123.crypt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String content0 = "123qwe";
        String password = "c6277625e9a69e13";
        byte[] value0 = content0.getBytes();
        byte[] passd0 = password.getBytes();
        LogUtils.d("content0:" + content0 + ", password:" + password);
        LogUtils.d("value0  :" + StringUtils.bytesToHexString(value0) + ", password:" + StringUtils.bytesToHexString(passd0));

        {
            byte[] value1 = AesUtils.encrypt1(content0.getBytes(), password);
            String content1 = new String(value1);
            LogUtils.d("content1:" + content1);
            LogUtils.d("envalue1:" + StringUtils.bytesToHexString(value1));

            byte[] value2 = AesUtils.decrypt1(value1, password);
            String content2 = new String(value2);
            LogUtils.d("content2:" + content2);
            LogUtils.d("devalue2:" + StringUtils.bytesToHexString(value2));
        }
        {
            byte[] value3 = AesUtils.encrypt2(content0.getBytes(), password);
            String content3 = new String(value3);
            LogUtils.d("content3:" + content3);
            LogUtils.d("envalue3:" + StringUtils.bytesToHexString(value3));

            byte[] value4 = AesUtils.decrypt2(value3, password);
            String content4 = new String(value4);
            LogUtils.d("content4:" + content4);
            LogUtils.d("devalue4:" + StringUtils.bytesToHexString(value4));
        }

        {
            byte[] value5 = Base64Utils.encode1(AesUtils.encrypt1(content0.getBytes(), password));
            String content5 = new String(value5);
            LogUtils.d("content5:" + content5);
            LogUtils.d("envalue5:" + StringUtils.bytesToHexString(value5));

            byte[] value6 = AesUtils.decrypt2(Base64Utils.decode(value5), password);
            String content6 = new String(value6);
            LogUtils.d("content6:" + content6);
            LogUtils.d("devalue6:" + StringUtils.bytesToHexString(value6));
        }

        Native.init(content0, password);
    }
}