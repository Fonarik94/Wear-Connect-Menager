package com.fonarik94.ConnectManager;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class MonitoringService extends Service {
    private DozeListener dozeListener = new DozeListener();

    @Override
    public void onCreate() {
        Log.d(MainActivity.TAG, "Service Created");
        registerReceiver();
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MainActivity.TAG, "Service onStartCommand");
        registerReceiver();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(MainActivity.TAG, "Service destroyed");
        unRegisterReceiver();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MainActivity.TAG, "onBind");
        return null;
    }

    private void registerReceiver() {
        this.registerReceiver(dozeListener, new IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED));
        this.registerReceiver(dozeListener, new IntentFilter(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED));
    }

    private void unRegisterReceiver() {
        this.unregisterReceiver(dozeListener);
    }
}
