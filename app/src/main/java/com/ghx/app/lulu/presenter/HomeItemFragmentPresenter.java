package com.ghx.app.lulu.presenter;

import android.os.Bundle;
import android.os.Message;

import com.ghx.app.base.BasePresenter;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.utils.LogUtil;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

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

        //请求数据
        getAdsServerData();
    }

    //http://capi.douyucdn.cn/api/v1/slide/6?version=2.301&client_sys=android
    public interface LunbotuService {
        @GET("6")
        Call<LunbotuBean> getDouyuLunbotu(@Query("version") String version, @Query("client_sys") String client_sys);

    }

    private void getAdsServerData() {

        String baseUrl = "http://capi.douyucdn.cn/api/v1/slide/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LunbotuService lunbotuService = retrofit.create(LunbotuService.class);
        Call<LunbotuBean> call = lunbotuService.getDouyuLunbotu("2.301", "android");

        call.enqueue(new Callback<LunbotuBean>() {
            @Override
            public void onResponse(Call<LunbotuBean> call, Response<LunbotuBean> response) {
                LogUtil.i_log(response.body().toString());
                iView.showAds(response.body());
            }

            @Override
            public void onFailure(Call<LunbotuBean> call, Throwable t) {
                String s = t.getMessage();
                LogUtil.i_log(s);
            }
        });
    }

    //http://capi.douyucdn.cn/api/v1/live?offset=0&limit=20&client_sys=android
    public interface ItemDataService {
        @GET("live")
        Call<HomeItemRvItemModel> getItemData(@Query("offset") String offset, @Query("limit") String limit, @Query("client_sys") String client_sys);

    }


    public void getItemServerData() {
        String baseUrl = "http://capi.douyucdn.cn/api/v1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemDataService itemDataService = retrofit.create(ItemDataService.class);
        Call<HomeItemRvItemModel> call = itemDataService.getItemData("0", "20", "android");

        call.enqueue(new Callback<HomeItemRvItemModel>() {
            @Override
            public void onResponse(Call<HomeItemRvItemModel> call, Response<HomeItemRvItemModel> response) {
                LogUtil.i_log(response.body().toString());
                //处理
                iView.showItem(response.body());
            }

            @Override
            public void onFailure(Call<HomeItemRvItemModel> call, Throwable t) {
                String s = t.getMessage();
                LogUtil.i_log(s);
            }
        });
    }

}
