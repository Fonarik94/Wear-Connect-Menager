package com.fonarik94.ConnectManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private String wf;
    private String bt;
    private ConnectivityManager mConnectivityManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mConnectivityManager = new ConnectivityManager(context);
        if (new SettingsManager(context.getApplicationContext()).getChargingDisablingOn()) {
            switch (intent.getAction()) {
                case Intent.ACTION_POWER_CONNECTED:
                    radioDisable();
                    Log.d(MainActivity.TAG, "power connected");
                    Toast.makeText(context, "bt: " + bt + "\nwifi: " + wf, Toast.LENGTH_SHORT).show();
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    radioDefault();
                    Log.d(MainActivity.TAG, "power disconnected");
                    Toast.makeText(context, "bt: " + bt + "\nwifi: " + wf, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    /*NOP*/
                    break;
            }
        }
    }
//turning off BT and WIFI modules
    private void radioDisable() {
        mConnectivityManager.wifiDisable();
        bt = mConnectivityManager.bluetoothDisable() ? "off" : "on";
        wf = mConnectivityManager.isWifiEnabled() ? "on" : "off";
    }
//returning radio modules to user default state
    private void radioDefault() {
        try {
            mConnectivityManager.backToDefaultState();
        } catch (RuntimeException e) {
                        /*NOP*/
            Log.e("PROJECTALPHA", "radioDefault: " + e.getMessage());
        }
        bt = mConnectivityManager.bluetoothEnable() ? "en" : "dis";
        wf = mConnectivityManager.isWifiEnabled() ? "en" : "dis";
    }

}
