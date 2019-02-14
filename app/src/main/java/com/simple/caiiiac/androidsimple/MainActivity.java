package com.simple.caiiiac.androidsimple;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        // 筛选条件
        Criteria criteria = new Criteria();
        // 精准度
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 海拔要求
        criteria.setAltitudeRequired(false);
        // 允许产生资费
        criteria.setCostAllowed(true);

        // 获取最佳服务对象
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            Log.i("c","provider: " + provider);
        }
//        locationManager.getLastKnownLocation(provider);
    }

}