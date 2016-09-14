package com.fonarik94.ConnectManager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;

public class ConnectivityManager {

    private WifiManager mWifiManager;
    private Context mContext;

    //Turn on wifi module
    public boolean wifiEnable() {
        boolean wifiSucsessed = false;
        if (!mWifiManager.isWifiEnabled()) {
            wifiSucsessed = mWifiManager.setWifiEnabled(true);
        }
        return wifiSucsessed;
    }

    //Turn off wifi module
    public void wifiDisable() {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        }
    }

    //Enables bluetooth module
    public boolean bluetoothEnable() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            boolean bt = false;
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                bt = BluetoothAdapter.getDefaultAdapter().enable();
            }
            return bt;
        } else {
//            throw new RuntimeException("Device has not bluetooth adapter");
            return false;
        }
    }

    //Disable bluetooth module
    public boolean bluetoothDisable() {
        return (BluetoothAdapter.getDefaultAdapter() != null && BluetoothAdapter.getDefaultAdapter().isEnabled()) && BluetoothAdapter.getDefaultAdapter().disable();
    }

    //Return bluetooth and wifi state to default state
    public void backToDefaultState() {
        if (BluetoothAdapter.getDefaultAdapter() != null && BluetoothAdapter.getDefaultAdapter().isEnabled() != new SettingsManager(mContext).getUserBluetoothState()) {
            BluetoothAdapter.getDefaultAdapter().enable();
        }
        if (mWifiManager.isWifiEnabled() != new SettingsManager(mContext).getUserWifiState()) {
            mWifiManager.setWifiEnabled(new SettingsManager(mContext).getUserWifiState());
        }
    }

    //Returns true - wifi enabled and false - if wifi disabled
    public boolean isWifiEnabled() {
        return mWifiManager.isWifiEnabled();
    }

    //Returns true - bt enabled and false - if bt disabled
    public boolean isBluetoothEnabled() {
        return BluetoothAdapter.getDefaultAdapter() !=null && BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    ConnectivityManager(Context mContext) {
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        this.mContext = mContext;
    }

}
