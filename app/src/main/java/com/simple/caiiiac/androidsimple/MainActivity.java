package com.simple.caiiiac.androidsimple;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    private LocationManager locationManager;
    private MyLocationListener locationListener;
    private Location mLocation;
    private Geocoder geocoder;


    private Handler locationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Event(R.id.getBtn)
    private void onClickDownload(View view) {

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        geocoder = new Geocoder(this);

        locationHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        getLocationInfo(mLocation);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        };
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
            Log.i("c", "provider: " + provider);
        }


        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                checkLocationPermission(this)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, locationListener);
        }
//        locationManager.getLastKnownLocation(provider);
    }

    private boolean checkLocationPermission(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("c","无权限 ");
            return false;
        }
        return true;
    }

    private void getLocationInfo(final Location location) {

        new Thread() {
            @Override
            public void run() {
                boolean flag = Geocoder.isPresent();

                if (flag) {
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 2);
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            String sAddress;
                            if (!TextUtils.isEmpty(address.getLocality())) {
                                if (!TextUtils.isEmpty(address.getFeatureName())) {
                                    //市和周边地址
                                    sAddress = address.getLocality() + " " + address.getFeatureName();
                                } else {
                                    sAddress = address.getLocality();
                                }
                            } else {
                                sAddress = "定位失败";
                            }
                            Log.i("c", "sAddress：" + sAddress);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    @Override
    protected void onStop() {
        super.onStop();

        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private final class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.i("c","onLocationChanged: " + location.toString());
            mLocation = location;
            Message msg = locationHandler.obtainMessage();
            msg.what = 0;
            locationHandler.sendMessage(msg);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("c","onStatusChanged: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("c","onProviderEnabled: " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("c","onProviderDisabled: " + provider);
        }
    }
}