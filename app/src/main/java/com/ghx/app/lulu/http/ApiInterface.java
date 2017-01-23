package com.ghx.app.lulu.http;


import android.support.annotation.IntDef;


import com.ghx.app.lulu.http.responses.EmptyResponse;
import com.ghx.app.lulu.model.HomeItemRvItemModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by guo_hx on 17/1/10.
 */
public interface ApiInterface {
    String URL_BASE = "http://capi.douyucdn.cn/api/v1/";

    @GET("live")
    Observable<HomeItemRvItemModel> getItemServerData(@Query("offset") int offset, @Query("limit") String limit, @Query("client_sys") String client_sys);
}
