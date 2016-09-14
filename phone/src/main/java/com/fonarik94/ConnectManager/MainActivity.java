package com.fonarik94.ConnectManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        connectivityManager = new ConnectivityManager(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        BroadcastReceiver batt = new PowerConnectionReceiver();
        IntentFilter power = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        this.registerReceiver(batt, power);*/
    }



}
