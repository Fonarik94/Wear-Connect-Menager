package com.fonarik94.ConnectManager;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends WearableActivity {

    private BoxInsetLayout mContainerView;

    public MainActivity() {
        super();
    }


    private ToggleButton chargingTButton;
    private ToggleButton dozeTButton;
    public static ConnectivityManager connectivityManager;
    public BatteryManager bm;
    private static Vibrate vibrate;
    private WifiManager wifiManager;
    public static final String TAG = "ProjectAlpha";

    public static SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        bm = (BatteryManager)this.getSystemService(BATTERY_SERVICE);
        chargingTButton = (ToggleButton) findViewById(R.id.chargingButton);
        dozeTButton = (ToggleButton) findViewById(R.id.dozeTButton);
        connectivityManager = new ConnectivityManager(MainActivity.this);

        vibrate = new Vibrate(MainActivity.this);
        settingsManager = new SettingsManager(this);
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

/*        for(Method method: wifiManager.getClass().getDeclaredMethods()){
            Log.d("WIFI_Reflection","name: "+method.getName()+" acces: "+method.getModifiers() );
        }*/

        startMonitoringService();
        buttonsStateSetter(); //set "checked" fof toggle buttons from settings file

    }


    public void buttonsStateSetter() {
        chargingTButton.setChecked(settingsManager.getChargingDisablingOn());
        dozeTButton.setChecked(settingsManager.getDozeDisablingOn());
    }

    private void startMonitoringService() {
        if (settingsManager.getDozeDisablingOn()) {
            startService(new Intent(this, MonitoringService.class));
        }
    }

    public void setUserDefault(View view) {
/*        settingsManager.setUserBluetoothState(connectivityManager.isBluetoothEnabled());
        settingsManager.setUserWifiState(connectivityManager.isWifiEnabled());
        Vibrate v = new Vibrate(MainActivity.this.getApplicationContext());
        v.vibrate(5, 60, 20, 50);
        String bt = connectivityManager.isBluetoothEnabled() ? "on" : "off";
        String wf = connectivityManager.isWifiEnabled() ? "on" : "off";
        Toast.makeText(MainActivity.this, "Set user" + "\nbt: " + bt + "\nwifi: " + wf, Toast.LENGTH_SHORT).show();*/
/*        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        Toast.makeText(this, String.valueOf(wifiManager.getWifiState()), Toast.LENGTH_SHORT).show();*/


        /*Class c = wifiManager.getClass();
        Class[] paramTypes = new Class[0] ;
        Method method = c.getMethod("getEnableAutoJoinWhenAssociated", paramTypes);
        Object[] args = new Object[0];
        boolean wtf = false;
                wtf = (boolean) method.invoke(wifiManager, args);
        */
        boolean wifi = wifiManager.isWifiEnabled()?wifiManager.setWifiEnabled(false):wifiManager.setWifiEnabled(true);
        Toast.makeText(this, "wifi:" + String.valueOf(wifi) , Toast.LENGTH_LONG).show();
    }

    public void chargingOnCheckedChanged(View view) {

        if (chargingTButton.isChecked()) {
            vibrate.vibrate(600);
            chargingTButton.setBackgroundColor(getColor(R.color.circular_button_pressed));
            settingsManager.setUserChargingDisablingOn(true);
            if (bm.isCharging()){
                connectivityManager.bluetoothDisable();
                connectivityManager.wifiDisable();
            }
        } else {
            vibrate.vibrate(600);
            chargingTButton.setBackgroundColor(getColor(R.color.circular_button));
            connectivityManager.backToDefaultState();
            settingsManager.setUserChargingDisablingOn(false);
        }

    }

    public void dozeOnCheckedChanged(View view){
        if (dozeTButton.isChecked()) {
            vibrate.vibrate(600);
            dozeTButton.setBackgroundColor(getColor(R.color.md_purple_700));
            settingsManager.setUserDozeDisablingOn(true);
        } else {
            vibrate.vibrate(600);
            dozeTButton.setBackgroundColor(getColor(R.color.md_purple_A700));
            settingsManager.setUserDozeDisablingOn(false);
        }
    }


    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {

        super.onDestroy();
    }

    private void updateDisplay() {

        if (isAmbient()) {
//            mContainerView.setBackgroundColor(getColor(android.R.color.black));
            mContainerView.setBackground(null);
            this.finish();

        } else {
            mContainerView.setBackground(null);
        }
    }

}