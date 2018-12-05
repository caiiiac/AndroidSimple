package com.simple.caiiiac.androidsimple;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Event(value = R.id.openSecond)
    private void onClickSecond(View view) {

        Intent second = new Intent(this, SecondActivity.class);
        second.putExtra("title", "第二层界面");
        second.putExtra("person", new Person("二层楼", 18));
        startActivity(second);
    }

    @Event(value = R.id.openSMS)
    private void onClickSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:10000"));
        intent.putExtra("sms_body", "这是短信内容");
        startActivity(intent);
    }
}