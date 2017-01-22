package com.ghx.app.lulu.http;

import android.text.TextUtils;

import com.ghx.app.lulu.utils.DigestUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_hx on 17/1/11.
 */
public class SignUrlInterceptor implements Interceptor {


    public static String randomStr() throws NoSuchAlgorithmException {
        String value = "Hi" + (new Date().getTime()) + (Math.random());
        return DigestUtil.getMD5(value);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();

            String nonce = randomStr();

            List<String> keys = new ArrayList<>();

            for (int i = 0; i < request.url().querySize(); i++) {
                String k = request.url().queryParameterName(i);
                if (!k.endsWith("]")) {
                    keys.add(k);
                }
            }
            keys.add("nonce");

            Collections.sort(keys);

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < keys.size(); i++) {
                String k = keys.get(i);
                String v = k.equals("nonce") ? nonce : request.url().queryParameter(k);
                if (!TextUtils.isEmpty(v)){
                    sb.append(k+'='+v);
                    sb.append('&');
                }
            }
            sb.append("key=");
            sb.append("3541917349470978669F5EE891BB107C");

            String sign = DigestUtil.getMD5(sb.toString()).toUpperCase();


            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("nonce",nonce)
                    .addQueryParameter("sign", sign)
                    .build();

            request = request.newBuilder().url(url).build();

            Response resp = chain.proceed(request);
            return resp;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
