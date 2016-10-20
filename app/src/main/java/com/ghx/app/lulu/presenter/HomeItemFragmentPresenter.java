package com.ghx.app.lulu.presenter;

import android.os.Bundle;
import android.os.Message;

import com.ghx.app.base.BasePresenter;
import com.ghx.app.lulu.http.base.BaseResponse;
import com.ghx.app.lulu.http.base.HttpTool;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.utils.ToastUtil;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guo_hx on 2016/9/26.17:12
 */

public class HomeItemFragmentPresenter extends BasePresenter<IHomeItemFragmentView> {

    @Override
    public void handleMsg(Message msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void viewShow() {

        super.viewShow();
    }


    public void getAdsServerData() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("version", "2.301");
        hashMap.put("client_sys", "android");

        HttpTool.getInstance(getActivity()).get("slide/6", hashMap, new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse BaseResponse) {
                ToastUtil.showToast("???");
            }

        });
    }

    //http://capi.douyucdn.cn/api/v1/live?offset=0&limit=20&client_sys=android
    public interface ItemDataService {

        @GET("live")
        Observable<HomeItemRvItemModel> getItemData(@Query("offset") int offset, @Query("limit") String limit, @Query("client_sys") String client_sys);
    }

    public void getItemServerData(int index) {
        String baseUrl = "http://capi.douyucdn.cn/api/v1/";

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
                });



        /*call.enqueue(new Callback<HomeItemRvItemModel>() {
            @Override
            public void onResponse(Call<HomeItemRvItemModel> call, Response<HomeItemRvItemModel> response) {
                LogUtil.i_log(response.body().toString());
                //处理
                iView.showItem(response.body());
            }

            @Override
            public void onFailure(Call<HomeItemRvItemModel> call, Throwable t) {
                String s = t.getMessage();
                LogUtil.i_log(s + "");
            }
        });*/
    }

}
