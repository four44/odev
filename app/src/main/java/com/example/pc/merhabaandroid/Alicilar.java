package com.example.pc.merhabaandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class Alicilar extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        int batarya = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 20);
        String batarya_durumu = "Batarya Sarj Durumu : %" + String.valueOf(batarya);
        Toast.makeText(context, batarya_durumu, Toast.LENGTH_LONG ).show();

    }
}
