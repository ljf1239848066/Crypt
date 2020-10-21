package com.lxzh123.crypt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String content0 = "qwe123";
        String password = "1234567899876543";
        byte[] value0 = content0.getBytes();
        byte[] passd0 = password.getBytes();

        LogUtils.d("content0:" + content0 + ", password:" + password);
        LogUtils.d("value0  :" + StringUtils.bytesToHexString(value0) + ", password:" + StringUtils.bytesToHexString(passd0));

        byte[] value1 = AesUtils.encrypt1(content0.getBytes(), password);
        String content1 = new String(value1);
        LogUtils.d("content1:" + content1);
        LogUtils.d("envalue1:" + StringUtils.bytesToHexString(value1));

        byte[] value2 = AesUtils.decrypt1(value1, password);
        String content2 = new String(value2);
        LogUtils.d("content2:" + content2);
        LogUtils.d("devalue2:" + StringUtils.bytesToHexString(value2));

        Native.init(content0, password);
    }
}