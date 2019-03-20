package com.simple.caiiiac.androidsimple;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIService {
    @POST("/commonIF/getToken.html")
    Call<ResponseBody> getToken(@Body Map <String, String> params);

    @POST("appIF/S02IF2001.html")
    Call<ResponseBody> getAll();
}
