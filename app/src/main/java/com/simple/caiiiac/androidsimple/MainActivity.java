package com.simple.caiiiac.androidsimple;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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


import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

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


    public LocationClient mLocationClient = null;
    private MyBDLocationListener myListener = new MyBDLocationListener();

    private Handler locationHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initLocation();

        bdLocation();

    }

    private void initLocation() {
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        geocoder = new Geocoder(this);
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

    }

    @Event(R.id.getBtn)
    private void onClickDownload(View view) {

//        systemLocation();

        mLocationClient.start();
    }

    // 系统gps定位
    private void systemLocation() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                checkLocationPermission(this)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, locationListener);
        }

    }

    private void bdLocation() {

        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);


        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
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


    public class MyBDLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息

            Log.i("c","成功");
        }
    }


}