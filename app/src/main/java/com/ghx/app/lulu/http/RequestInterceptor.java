package com.ghx.app.lulu.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by guo_hx on 17/1/16.
 */
// 这个其实是解密模块
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody body = response.body();
        byte[] bytes = body.bytes();

        ResponseBody outBody;
        if (bytes[0] == -126) {
//            bytes = WebSocket.recvResp(bytes);
        }

        outBody = ResponseBody.create(body.contentType(), bytes);

        return response.newBuilder()
                .headers(response.headers())
                .body(outBody)
                .build();
    }
}
