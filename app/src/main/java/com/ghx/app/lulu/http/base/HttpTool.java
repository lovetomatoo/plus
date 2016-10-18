package com.ghx.app.lulu.http.base;

import com.ghx.app.lulu.http.servicehttp.LunbotuService;
import com.ghx.app.lulu.model.LunbotuBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guo_hx on 2016/10/18.10:39
 * <p>
 * Http请求的工具类
 */

public class HttpTool {

    private static HttpTool instance = new HttpTool();

    private static final long TIMEOUT_DEFAULT = 5;
    private Retrofit mRetrofit;
    private final LunbotuService mLunbotuService;

    private HttpTool() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://capi.douyucdn.cn/api/v1/slide/")
                .build();

        mLunbotuService = mRetrofit.create(LunbotuService.class);

    }

    public static HttpTool getInstance() {
        return instance;
    }


    public void getAdsServerData (Subscriber<LunbotuBean> subscriber,
                                  String version,
                                  String client_sys) {
        mLunbotuService.getDouyuLunbotu("version", "client_sys")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultFunc<List<LunbotuService>>());

    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<BaseRequest<T>, T> {

        @Override
        public T call(BaseRequest<T> baseRequest) {
            if (baseRequest.getError() == 0) {
//                throw new ApiException(100);
            }
            return baseRequest.getData();
        }
    }
}
