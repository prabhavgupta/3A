package com.example.prabgupt.a4a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public class HeadsetPlugReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                return;
            }

            boolean connectedHeadphones = (intent.getIntExtra("state", 0) == 1);
            boolean connectedMicrophone = (intent.getIntExtra("microphone", 0) == 1) && connectedHeadphones;
            String headsetName = intent.getStringExtra("name");

            if(connectedMicrophone)
                Toast.makeText(context, "earphone connected: "+headsetName, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "HEADSET DISCONNECTED", Toast.LENGTH_SHORT).show();
        }
    }

    HeadsetPlugReceiver headsetPlugReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headsetPlugReceiver = new HeadsetPlugReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(headsetPlugReceiver, intentFilter);
    }
    @Override
    protected void onDestroy() {
        if (headsetPlugReceiver != null) {
            unregisterReceiver(headsetPlugReceiver);
            headsetPlugReceiver = null;
        }

        super.onDestroy();
    }
}
