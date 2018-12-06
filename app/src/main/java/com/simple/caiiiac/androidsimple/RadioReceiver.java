package com.simple.caiiiac.androidsimple;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RadioReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("caiiiac", "收到Radio广播消息");
    }
}
