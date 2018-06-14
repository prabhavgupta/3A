package com.example.prabgupt.a4a;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MicService extends Service {
    public MicService() {
    }
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
                Log.i("amanmic","earphone connected");
//                Toast.makeText(context, +headsetName, Toast.LENGTH_SHORT).show();
            else
                Log.i("amanmic","earphone disconnected");
//                Toast.makeText(context, "HEADSET DISCONNECTED", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    HeadsetPlugReceiver headsetPlugReceiver;
    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.d("MicService", "FirstService started");
        headsetPlugReceiver = new HeadsetPlugReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(headsetPlugReceiver, intentFilter);
//        this.stopSelf();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("MicService", "FirstService destroyed");
    }

}
