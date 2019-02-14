package com.simple.caiiiac.androidsimple;

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


    @Event(R.id.getBtn)
    private void onClickDownload(View view) {

        RequestParams params = new RequestParams(top250());

        x.http().get(params, new Callback.CommonCallback<HashMap>() {

            @Override
            public void onSuccess(HashMap result) {
                Log.d("caiiiac","success");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("caiiiac","e");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("caiiiac","c");
            }

            @Override
            public void onFinished() {
                Log.d("caiiiac","f");
            }
        });
    }

    public String top250() {
        return "http://api.douban.com/v2/movie/top250";
    }

}