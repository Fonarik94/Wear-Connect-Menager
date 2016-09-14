package com.fonarik94.ConnectManager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import android.widget.Toast;

public class DozeListener extends BroadcastReceiver {
    private String wf;
    private String bt;
    private PowerManager pm;
    private ConnectivityManager mConnectivityManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mConnectivityManager = new ConnectivityManager(context);

        if (pm.isDeviceIdleMode()) {
            radioDisable(context);
            //for debug
            Toast.makeText(context, "bt: " + bt + "\nwifi: " + wf, Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.TAG, "Idle");
        } else {
            radioDefault(context);
            //for debug
            Toast.makeText(context, "bt: " + bt + "\nwifi: " + wf, Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.TAG, "Active");
        }

    }

    private void radioDisable(Context mContext) {
        new ConnectivityManager(mContext).wifiDisable();
        bt = mConnectivityManager.bluetoothDisable() ? "off" : "on";
        wf = mConnectivityManager.isWifiEnabled() ? "on" : "off";
    }
//---------------------------------------
    private void radioDefault(Context mContext) {
        new ConnectivityManager(mContext).backToDefaultState();
        bt = mConnectivityManager.bluetoothEnable() ? "on" : "off";
        wf = mConnectivityManager.isWifiEnabled() ? "on" : "off";
    }
}
