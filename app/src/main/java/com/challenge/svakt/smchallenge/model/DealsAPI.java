package com.challenge.svakt.smchallenge.model;


import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by svakt on 01/05/2017.
 */

public class DealsAPI {
    private static final String ROOT_URL = "https://s3-ap-southeast-2.amazonaws.com/lux-mobile-dev/";
    private static File cacheDirectory;
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(getDirectory(), cacheSize);
    private static String UserAgent = "MyLuxDeals";

    private static File getDirectory() {
        return new File("network_cache");
    }

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new UserAgentInterceptor(UserAgent))
            .cache(cache)
            .build();


    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DealService getDealsListAPI() {
        return getRetrofitInstance().create(DealService.class);
    }
}
