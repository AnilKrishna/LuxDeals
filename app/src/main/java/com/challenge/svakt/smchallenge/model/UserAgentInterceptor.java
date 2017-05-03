package com.challenge.svakt.smchallenge.model;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by svakt on 3/05/2017.
 */

public class UserAgentInterceptor implements Interceptor {

    private final String userAgent;

    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request requestWithUserAgent = originRequest.newBuilder()
                .header("User-Agent", userAgent)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
