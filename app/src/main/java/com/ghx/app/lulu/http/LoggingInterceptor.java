package com.ghx.app.lulu.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by guo_hx on 17/1/27.
 */
class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);

        ResponseBody body = response.body();

        String bodyString = body.string();

        return response.newBuilder()
                .headers(response.headers())
                .body(ResponseBody.create(body.contentType(), bodyString))
                .build();
    }

}