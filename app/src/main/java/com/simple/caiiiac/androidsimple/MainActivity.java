package com.simple.caiiiac.androidsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request();
                Log.i("c", "d");
            }
        });
    }

    private void request() {

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
//                .addNetworkInterceptor(new Retry(2))
                .addInterceptor(new Retry(2))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.252:8070")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        APIService service = retrofit.create(APIService.class);
        Map<String, String> params = new HashMap<>();
        params.put("orgCode", "1000");
        params.put("token", "sBank1");
        params.put("timestamp", "1551076449000");
        params.put("signature", "39A632F3C90B3403636EFFF4D22D9D24");

        Call<ResponseBody> call = service.getToken(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.i("c", result);
                } catch (IOException e) {
                    Log.i("c", "IO");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.i("c", "出错");
            }
        });
    }
}
