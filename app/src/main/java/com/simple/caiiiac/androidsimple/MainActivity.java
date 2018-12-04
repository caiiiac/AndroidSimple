package com.simple.caiiiac.androidsimple;

import android.os.Bundle;
import android.util.JsonReader;
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
//        setContentView(R.layout.activity_main);
//        x.view().inject(this);


    }

    @Event(value = R.id.getBtn)
    private void onClickDownload(View view) {
        Log.d("caiiiac","点击了");
        RequestParams params = new RequestParams(top250());
//        params.addQueryStringParameter("wd","android");


        x.http().get(params, new Callback.CommonCallback<HashMap>() {

//            @Override
//            public void onSuccess(String result) {
//                Log.d("caiiiac","success");
//            }

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

    public void get() {

    }

    public String top250() {
        return "http://api.douban.com/v2/movie/top250";
    }

    public String baidu() {
        return "https://www.baidu.com/s";
    }
}