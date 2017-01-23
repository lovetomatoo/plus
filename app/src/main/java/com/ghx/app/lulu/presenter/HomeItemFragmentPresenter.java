package com.ghx.app.lulu.presenter;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.ghx.app.base.BasePresenter;
import com.ghx.app.lulu.http.ApiClient;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.utils.LogUtil;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by guo_hx on 2016/9/26.17:12
 */

public class HomeItemFragmentPresenter extends BasePresenter<IHomeItemFragmentView> {

    private String TAG = getClass().getSimpleName();

    @Override
    public void handleMsg(Message msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getItemServerData(0);
        getAdsServerData();
    }

    @Override
    public void viewShow() {

        super.viewShow();
    }


    public void getAdsServerData() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("version", "2.301");
        hashMap.put("client_sys", "android");

//        HttpTool.getInstance(getActivity()).getData(new Subscriber<LunbotuBean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(LunbotuBean lunbotuBean) {
//               iView.showAds(lunbotuBean);
//            }
//
//        }, "2.301", "android");
    }

    //http://capi.douyucdn.cn/api/v1/live?offset=0&limit=20&client_sys=android
//    public interface ItemDataService {
//        @GET("live")
//        Observable<HomeItemRvItemModel> getItemData(@Query("offset") int offset, @Query("limit") String limit, @Query("client_sys") String client_sys);
//    }

    public void getItemServerData(int index) {
        /*String baseUrl = "http://capi.douyucdn.cn/api/v1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ItemDataService itemDataService = retrofit.create(ItemDataService.class);
        itemDataService.getItemData(index, "20", "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeItemRvItemModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeItemRvItemModel homeItemRvItemModel) {
                        iView.showItem(homeItemRvItemModel);
                    }
                });*/
        LogUtil.i_log(TAG, "TEST");

        ApiClient.getInstance().getItemServerData(index, "20", "android").subscribe(new Action1<HomeItemRvItemModel>() {
            @Override
            public void call(HomeItemRvItemModel homeItemRvItemModel) {
                LogUtil.i_log(TAG, homeItemRvItemModel.data.get(0).nickname);
                LogUtil.i_log(TAG, Thread.currentThread().getName());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtil.i_log(TAG, throwable.toString());
            }
        });
    }

}
