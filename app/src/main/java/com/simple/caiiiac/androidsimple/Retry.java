package com.simple.caiiiac.androidsimple;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retry implements Interceptor {

    public int maxRetry;
    private int retryNum = 0;
    public  Retry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        RequestBody body = request.body();


        Log.i("c", getToken());
        if (body != null) {
            okio.Buffer buffer = new okio.Buffer();
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String string = buffer.readString(charset);
            Log.i("c",string);
            Map<String, String> params = new HashMap<>();
            params.put("orgCode", "1000");
            params.put("token", "sBank");
            params.put("timestamp", "1551076449000");
            params.put("signature", "39A632F3C90B3403636EFFF4D22D9D24");
            Gson gson = new Gson();
            String encryptStr = gson.toJson(params);
            Log.i("c",encryptStr);
            RequestBody newBody = MultipartBody.create(contentType, encryptStr);
            request = request.newBuilder()
                    .post(newBody)
                    .build();
            response = chain.proceed(request);

        }

//        request.body().close();
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;

            response = chain.proceed(request);
        }


        return response;
    }


    private String getToken() throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
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
        params.put("token", "sBank");
        params.put("timestamp", "1551076449000");
        params.put("signature", "39A632F3C90B3403636EFFF4D22D9D24");

        Call<ResponseBody> call = service.getToken(params);
        return call.execute().body().string();
    }
}
