package com.fonarik94.ConnectManager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by Ярослав on 14.08.2016.
 */
public class ConnectivityManager {


    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private WifiManager mWifiManager;

    private static boolean defaultBluetoothState;
    private static boolean defaultWifiState;

    //Togle wifi module state
    public void wifiToggle() {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        } else {
            mWifiManager.setWifiEnabled(true);
        }

    }

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

    //Toggle bluetooth module state
    public boolean bluetoothToggle() {
        boolean btToggleSucsess = false;
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isEnabled()) {
                return btToggleSucsess = mBluetoothAdapter.disable();
            } else {
                return btToggleSucsess = mBluetoothAdapter.enable();
            }

        } else {
            throw new RuntimeException("Device has not bluetooth module!");
        }

    }

    //Enables bluetooth module
    public boolean bluetoothEnable() {
        if (mBluetoothAdapter != null) {
            boolean bt = false;
            if (!mBluetoothAdapter.isEnabled()) {
                bt = mBluetoothAdapter.enable();
            }
            return bt;
        } else {
            throw new RuntimeException("Device has not bluetooth adapter");
        }
    }

    //Disable bluetooth module
    public boolean bluetoothDisable() {
        boolean bt = false;
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isEnabled()) {
                bt = mBluetoothAdapter.disable();
            }
            return bt;
        } else {
            throw new RuntimeException("Device has not bluetooth adapter");
        }

    }

    //Get's default state for wireless modules
    public void getDefaultState() {
        defaultBluetoothState = isBluetoothEnabled();
        defaultWifiState = isWifiEnabled();
    }

    //Return bluetooth and wifi state to default state
    public void backToDefaultState() {
        if (!(mBluetoothAdapter.isEnabled() == defaultBluetoothState)) {
            mBluetoothAdapter.enable();
        }
        if (mWifiManager.isWifiEnabled() == defaultWifiState) {
            mWifiManager.setWifiEnabled(defaultWifiState);
        }
    }

    //Returns true - wifi enabled and false - if wifi disabled
    public boolean isWifiEnabled() {
        return mWifiManager.isWifiEnabled();
    }

    //Returns true - wifi enabled and false - if wifi disabled
    public boolean isBluetoothEnabled() {
        return mBluetoothAdapter.isEnabled();
    }


    ConnectivityManager(Context mContext) {
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

}
