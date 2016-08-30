package com.king1033.rxjava_with_retrofit_demo.http;


import com.king1033.rxjava_with_retrofit_demo.bean.OpenServerBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/8/30
 */
public interface HttpService {
    @GET("/majax.action?method=getJtkaifu")
    Observable<OpenServerBean> queryOpenServer();
}

