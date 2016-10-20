package com.ghx.app.lulu.http.base;

import android.content.Context;

import com.ghx.app.lulu.model.LunbotuBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guo_hx on 2016/10/18.10:39
 * <p>
 * Http请求的工具类
 */

public class HttpTool {

    private static String mBaseUrl = ApiService.BASE_URL;
    private static HttpTool instance = new HttpTool();

    private static final long TIMEOUT_DEFAULT = 5;
    private static Retrofit.Builder mBuilder;
    private static Context mContext;
    private ApiService mApiService;
    private Retrofit mRetrofit;

    private HttpTool() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS);

        mBuilder = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl);
        mRetrofit = mBuilder.build();

        mApiService = create(ApiService.class);

    }

    public static HttpTool getInstance(Context context) {
        mContext = context;
        return instance;
    }


    public  <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        mBaseUrl = newApiBaseUrl;
        mBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl);
    }

    public Subscription getData(Subscriber<ResponseBody> subscriber, String version, String client_sys) {
        return mApiService.getDouyuLunbotu(version, client_sys)
                .compose(schedulersTransformer())
                .compose(transformer())
                .subscribe(subscriber);
    }

    public Subscription get(String url, Map parameters, Subscriber<BaseResponse> subscriber) {

        return mApiService.executeGet(url, parameters)
                .compose(schedulersTransformer())
                .compose(transformer())
                .subscribe(subscriber);
    }

    public void post(String url, Map<String, String> parameters, Subscriber<ResponseBody> subscriber) {
        mApiService.executePost(url, parameters)
                .compose(schedulersTransformer())
                .compose(transformer())
                .subscribe(subscriber);
    }

    public void upload(String url, RequestBody requestBody, Subscriber<ResponseBody> subscriber) {
        mApiService.upLoadFile(url, requestBody)
                .compose(schedulersTransformer())
                .compose(transformer())
                .subscribe(subscriber);
    }

    public void download(String url, final CallBack callBack) {
        mApiService.downloadFile(url)
                .compose(schedulersTransformer())
                .compose(transformer())
                .subscribe(new DownSubscriber<ResponseBody>(callBack));
    }




    Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {


            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }

           /* @Override
            public Observable call(Observable observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }*/
        };
    }

    public <T> Observable.Transformer<BaseResponse<T>, T> transformer() {

        return new Observable.Transformer() {

            @Override
            public Object call(Object observable) {
                return ((Observable) observable).map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

    private class HandleFuc<T> implements Func1<BaseResponse<T>, T> {
        @Override
        public T call(BaseResponse<T> response) {
            if (!response.isOk()) throw new RuntimeException(response.code + "" + response.msg != null ? response.msg: "");
            return response.data;
        }
    }

    private static class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override public Observable<T> call(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }


    /**
     * DownSubscriber
     * @param <ResponseBody>
     */
    class DownSubscriber<ResponseBody> extends Subscriber<ResponseBody> {
        CallBack callBack;

        public DownSubscriber(CallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onStart() {
            super.onStart();
            if (callBack != null) {
                callBack.onStart();
            }
        }

        @Override
        public void onCompleted() {
            if (callBack != null) {
                callBack.onCompleted();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (callBack != null) {
                callBack.onError(e);
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            DownLoadManager.getInstance(callBack).writeResponseBodyToDisk(mContext, (okhttp3.ResponseBody) responseBody);

        }
    }

}
