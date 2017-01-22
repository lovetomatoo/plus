package com.ghx.app.lulu.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import com.ghx.app.BuildConfig;
import com.ghx.app.base.FrameApplication;
import com.ghx.app.lulu.utils.Constants;
import com.ghx.app.lulu.utils.SharedPreferencesUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by guo_hx on 17/1/10.
 */
public class ApiClient {

    private static ApiInterface instance;

    public static ApiInterface getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                instance = init(FrameApplication.getAppInstance());
            }
        }
        return instance;
    }

    public static ApiInterface init(Context context) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new SessionInterceptor(context));

        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            okHttpClientBuilder.addNetworkInterceptor(new SignUrlInterceptorDebug());
            okHttpClientBuilder.addNetworkInterceptor(new LoggingInterceptor());
        } else {
            okHttpClientBuilder.addNetworkInterceptor(new SignUrlInterceptor());
        }

        okHttpClientBuilder.addNetworkInterceptor(new RequestInterceptor());
        okHttpClientBuilder.addNetworkInterceptor(new GzipInterceptor());
        okHttpClientBuilder.connectTimeout(Integer.parseInt(SharedPreferencesUtil.getStringData(context,
                Constants.NETWORK_TIMEOUT, "6")), TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(ApiInterface.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        return retrofit.create(ApiInterface.class);
    }

}
