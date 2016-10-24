package com.ghx.app.lulu.http.base;

import com.ghx.app.lulu.model.LunbotuBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by guo_hx on 2016/10/20.11:41
 */

public interface ApiService<T> {

    public static final String BASE_URL = "http://capi.douyucdn.cn/api/v1/";


    @GET("slide/6")
    Observable<BaseResponse<LunbotuBean>> getDouyuLunbotu(@Query("version") String version,
                                            @Query("client_sys") String client_sys);


    @GET("{url}")
    Observable<BaseResponse<T>> executeGet(
                    @Path("url") String url,
                    @QueryMap Map<String, String> maps
            );


    @POST("{url}")
    Observable<ResponseBody> executePost(
            @Path("url") String url,
            //  @Header("") String authorization,
            @QueryMap Map<String, String> maps);


    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);


    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);


    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}
