package com.ms.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建服务
        Intent intent = new Intent("com.ms.s");
        // 设置包名
        intent.setPackage("com.ms.app2");
        // 启动服务
        startService(intent);
    }
}