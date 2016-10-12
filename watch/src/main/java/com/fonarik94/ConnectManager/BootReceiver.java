package com.fonarik94.ConnectManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (new SettingsManager(context).getDozeDisablingOn()) {
            context.startService(new Intent(context, MonitoringService.class));
            Log.d(MainActivity.TAG, "Boot completed");
        }
    }
}
