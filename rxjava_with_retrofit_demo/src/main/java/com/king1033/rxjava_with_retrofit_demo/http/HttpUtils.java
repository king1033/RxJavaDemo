package com.king1033.rxjava_with_retrofit_demo.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by king1033 on 2016/8/27.
 *
 * @Author:
 * @Desc:
 * @Time:2016/8/27 16:35
 */
public class HttpUtils {

    private static HttpService mHttpService;
    private static String BASE_URL = "http://www.1688wan.com";

    public static HttpService create(){
        if(mHttpService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpService = retrofit.create(HttpService.class);
        }
        return mHttpService;
    }
}
