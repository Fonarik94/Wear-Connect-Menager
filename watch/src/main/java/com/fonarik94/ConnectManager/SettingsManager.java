package com.fonarik94.ConnectManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingsManager {

    private static SharedPreferences sharedPreferences;
    private static Editor editor;

    private static final String APP_PREFS = "StateSettings";
    private static final String CHARGING_DISABLING_STR = "CHARGING_DISABLING_STR";
    private static final String DOZE_DISABLING_STR = "DOZE_DISABLING_STR";
    private static final String USER_WIFI_STATE = "USER_WIFI_STATE";
    private static final String USER_BLUETOOTH_STATE = "USER_BLUETOOTH_STATE";
    public static final boolean DEFAULT_BLUETOOTH_STATE = true;
    public static final boolean DEFAULT_WIFI_STATE = false;
    public static final boolean DEFAULT_CHARGING_DISABLING_ON = false;
    public static final boolean DEFAULT_DOZE_DISABLING_ON = false;

    public boolean getChargingDisablingOn() {
        //                                  str                     def value
        return sharedPreferences.getBoolean(CHARGING_DISABLING_STR, DEFAULT_CHARGING_DISABLING_ON);
    }

    public boolean getDozeDisablingOn() {
        return sharedPreferences.getBoolean(DOZE_DISABLING_STR, DEFAULT_DOZE_DISABLING_ON);
    }

    public void setUserChargingDisablingOn(boolean chargingDisablingOn) {
        editor.putBoolean(CHARGING_DISABLING_STR, chargingDisablingOn);
        editor.apply();
    }

    public void setUserDozeDisablingOn(boolean dozeDisablingOn) {
        editor.putBoolean(DOZE_DISABLING_STR, dozeDisablingOn);
        editor.apply();
    }

    public void setUserWifiState(boolean userWifiState) {
        editor.putBoolean(USER_WIFI_STATE, userWifiState);
        editor.apply();
    }

    public void setUserBluetoothState(boolean userBluetoothState) {
        editor.putBoolean(USER_BLUETOOTH_STATE, userBluetoothState);
        editor.apply();
    }
//--------------------------------------------------------------
    public boolean getUserBluetoothState() {
        return sharedPreferences.getBoolean(USER_BLUETOOTH_STATE, DEFAULT_BLUETOOTH_STATE);


    }

    public boolean getUserWifiState() {
        return sharedPreferences.getBoolean(USER_WIFI_STATE, DEFAULT_WIFI_STATE);
    }

    SettingsManager(Context mContext) {

        sharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (!sharedPreferences.getBoolean("hasVisited", false)) {
            editor.putBoolean(CHARGING_DISABLING_STR, DEFAULT_CHARGING_DISABLING_ON);
            editor.putBoolean(DOZE_DISABLING_STR, DEFAULT_DOZE_DISABLING_ON);
            editor.putBoolean(USER_WIFI_STATE, MainActivity.connectivityManager.isWifiEnabled());
            editor.putBoolean(USER_BLUETOOTH_STATE, MainActivity.connectivityManager.isBluetoothEnabled());
            editor.putBoolean("hasVisited", true);
            editor.apply();
        }
    }
}

