package com.ghx.app.lulu.http.servicehttp;

import com.ghx.app.lulu.http.base.BaseRequest;
import com.ghx.app.lulu.model.LunbotuBean;

import java.util.List;

import javax.security.auth.Subject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by guo_hx on 2016/10/18.10:59
 */

//http://capi.douyucdn.cn/api/v1/slide/6?version=2.301&client_sys=android
public interface LunbotuService {

    @GET("6")
    Observable<LunbotuBean> getDouyuLunbotu(@Query("version") String version,
                                                               @Query("client_sys") String client_sys);
}
