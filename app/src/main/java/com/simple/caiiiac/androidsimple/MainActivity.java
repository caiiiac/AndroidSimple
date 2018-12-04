package com.simple.caiiiac.androidsimple;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageView = findViewById(R.id.imageView);
    }

    @Event(value = R.id.playBtn)
    private void startPlay(View view) {
        AnimationDrawable drawable = (AnimationDrawable) mImageView.getDrawable();
        drawable.start();
    }
}