package com.ghx.app.lulu.http;


import android.support.annotation.IntDef;


import com.ghx.app.lulu.http.responses.EmptyResponse;

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
    String URL_BASE = "";
    int PAGE_SIZE = 20;

    int REPORT_TYPE_OTHER_LIVE = 0;
    int REPORT_TYPE_PORNO_LIVE = 1;
    int REPORT_TYPE_POLITICAL_LIVE = 2;
    int REPORT_TYPE_AD_LIVE = 3;
    int REPORT_TYPE_ABUSE_LIVE = 4;

    int REPORT_TYPE_OTHER_USER_PAGE = 10;
    int REPORT_TYPE_PORNO_USER_PAGE = 11;
    int REPORT_TYPE_POLITICAL_USER_PAGE = 12;
    int REPORT_TYPE_AD_USER_PAGE = 13;
    int REPORT_TYPE_ABUSE_USER_PAGE = 14;

    int REPORT_TYPE_OTHER_PLAYBACK = 20;
    int REPORT_TYPE_PORNO_PLAYBACK = 21;
    int REPORT_TYPE_POLITICAL_PLAYBACK = 22;
    int REPORT_TYPE_AD_PLAYBACK = 23;
    int REPORT_TYPE_ABUSE_PLAYBACK = 24;

    @IntDef(value = {
            REPORT_TYPE_OTHER_LIVE, REPORT_TYPE_PORNO_LIVE, REPORT_TYPE_POLITICAL_LIVE, REPORT_TYPE_AD_LIVE, REPORT_TYPE_ABUSE_LIVE,
            REPORT_TYPE_OTHER_USER_PAGE, REPORT_TYPE_PORNO_USER_PAGE, REPORT_TYPE_POLITICAL_USER_PAGE, REPORT_TYPE_AD_USER_PAGE, REPORT_TYPE_ABUSE_USER_PAGE,
            REPORT_TYPE_OTHER_PLAYBACK, REPORT_TYPE_PORNO_PLAYBACK, REPORT_TYPE_POLITICAL_PLAYBACK, REPORT_TYPE_AD_PLAYBACK, REPORT_TYPE_ABUSE_PLAYBACK})
    @Retention(RetentionPolicy.SOURCE)
    @interface ReportType {
    }

    @FormUrlEncoded
    @POST("auth/login/findPassword")
    Observable<EmptyResponse> resetPassword(
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("verify") String verify);

}
