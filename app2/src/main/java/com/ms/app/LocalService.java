package com.ms.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private final IProto.Stub binder = new IProto.Stub() {
        @Override
        public void send(String message) throws RemoteException {
            // 接收到消息
            Log.e(TAG, "接收到消息: " + message);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}