package com.simple.caiiiac.androidsimple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.HashMap;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Event(R.id.sendBroadcastBtn)
    private void onClickSendBroadcast(View view) {
        Intent intent = new Intent("com.simple.action.RadioReceiver");
        intent.setPackage("com.simple.caiiiac.androidsimple");
        sendBroadcast(intent);
    }

}