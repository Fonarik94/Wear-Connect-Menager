package com.fonarik94.ConnectManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;


public class ConnectivityReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    SettingsManager settingsManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        settingsManager = new SettingsManager(context);
        if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION) && (settingsManager.getChargingDisablingOn()|| settingsManager.getDozeDisablingOn())) {
//                    Toast.makeText(context, intent.getAction()+wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
                    Log.d(MainActivity.TAG, "onReceive: " + intent.getAction()+ " "+ wifiManager.getWifiState() + ":");

            switch (wifiManager.getWifiState()){
                case WifiManager.WIFI_STATE_ENABLING:
                        new ConnectivityManager(context).wifiDisable();
                        Log.d(MainActivity.TAG, "WIFI_STATE_ENABLING");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                        new ConnectivityManager(context).wifiDisable();
                    Log.d(MainActivity.TAG, "WIFI_STATE_ENABLED");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.d(MainActivity.TAG, "WIFI_STATE_DISABLING");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.d(MainActivity.TAG, "WIFI_STATE_DISABLED");
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Log.d(MainActivity.TAG, "WIFI_STATE_Unknown");
                default:
                    Log.d(MainActivity.TAG, "Default: " + intent.getAction());
                    break;
            }
        }
    }
}
