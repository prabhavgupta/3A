package com.example.prabgupt.a4a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MicService.class));
    }

    @Override
    protected void onDestroy() {
//        if (headsetPlugReceiver != null) {
//            unregisterReceiver(headsetPlugReceiver);
//            headsetPlugReceiver = null;
//        }

        super.onDestroy();
    }
}
