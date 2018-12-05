package com.simple.caiiiac.androidsimple;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    private void onClickDownload(View view) {

        Intent second = new Intent(this, SecondActivity.class);
        second.putExtra("title", "第二层界面");
        second.putExtra("person", new Person("二层楼", 18));
        startActivity(second);

    }
}