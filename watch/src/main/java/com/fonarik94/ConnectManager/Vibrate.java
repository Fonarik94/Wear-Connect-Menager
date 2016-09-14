package com.fonarik94.ConnectManager;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import java.util.Arrays;

public class Vibrate {
    private Context mContext;

    private Vibrator vibro;

    public  void vibrate(int... vibratePattern) {
        vibro = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        long [] pattern = new long[vibratePattern.length];
        int i = 0;
        for(int v: vibratePattern ){
            pattern[i]=v;
            i++;
        }
        Log.d(MainActivity.TAG, Arrays.toString(pattern));
        vibro.vibrate(pattern, -1);

    }

    public void vibrate(int vibrateTime){
        vibro = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibro.vibrate(vibrateTime);
        vibro.cancel();
    }

    public Vibrate(Context mContext){
        this.mContext = mContext;
    }

}
