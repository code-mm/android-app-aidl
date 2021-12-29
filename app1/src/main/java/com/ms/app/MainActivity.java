package com.ms.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private IProto proto;
    private Button buttonStartService;
    private Button buttonSendMessage;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartService = findViewById(R.id.buttonStartService);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                proto = IProto.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                proto = null;
            }
        };

        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建意图
                Intent intent = new Intent("com.ms.s");
                // 设置包名
                intent.setPackage("com.ms.app2");
                // 绑定服务
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 发送消息
                    proto.send("Hello World");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}