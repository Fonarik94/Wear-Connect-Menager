package com.fonarik94.ConnectManager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class ConnectivityManager {

    private WifiManager mWifiManager;
    private Context mContext;

    //Turn off wifi module
    public boolean wifiDisable() {
        return mWifiManager.isWifiEnabled()&&mWifiManager.setWifiEnabled(false);
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
            return false;
        }
    }

    //Disable bluetooth module
    public boolean bluetoothDisable() {
        return (BluetoothAdapter.getDefaultAdapter() != null && BluetoothAdapter.getDefaultAdapter().isEnabled()) && BluetoothAdapter.getDefaultAdapter().disable();
    }

    //Return bluetooth and wifi state to default state
    public void backToDefaultState() {
        boolean btRes = false;
        boolean wifiRes = false;
        if (BluetoothAdapter.getDefaultAdapter() != null && BluetoothAdapter.getDefaultAdapter().isEnabled() != new SettingsManager(mContext).getUserBluetoothState()) {
            btRes = BluetoothAdapter.getDefaultAdapter().enable();
        }
        if (mWifiManager.isWifiEnabled() != new SettingsManager(mContext).getUserWifiState()) {
            wifiRes = mWifiManager.setWifiEnabled(new SettingsManager(mContext).getUserWifiState());
        }
        String bt = String.valueOf(btRes);
        String wifi = String.valueOf(wifiRes);
        Toast.makeText(mContext, "bt: " + bt + "\nwifi: " + wifi, Toast.LENGTH_SHORT).show();
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
