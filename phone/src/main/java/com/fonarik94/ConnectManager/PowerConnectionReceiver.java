package com.fonarik94.ConnectManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager mConnectivityManager = new ConnectivityManager(context);
        switch (intent.getAction()) {
            case Intent.ACTION_POWER_CONNECTED:
                Toast.makeText(context, "bt: " + String.valueOf(mConnectivityManager.isBluetoothEnabled()) + "\nwifi: " + String.valueOf(mConnectivityManager.isWifiEnabled()), Toast.LENGTH_SHORT).show();
                mConnectivityManager.bluetoothDisable();
                mConnectivityManager.wifiDisable();
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                mConnectivityManager.backToDefaultState();
                Toast.makeText(context, "bt: " + String.valueOf(mConnectivityManager.isBluetoothEnabled()) + "\nwifi: " + String.valueOf(mConnectivityManager.isWifiEnabled()), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}