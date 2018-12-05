package com.simple.caiiiac.androidsimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_second)
public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 普通传值
        TextView textView = (TextView) findViewById(R.id.secondTitle);
        textView.setText(getIntent().getStringExtra("title"));

        TextView personText = (TextView) findViewById(R.id.secondPerson);
        Person person = (Person) getIntent().getSerializableExtra("person");
        personText.setText(person.toString());
    }
}
