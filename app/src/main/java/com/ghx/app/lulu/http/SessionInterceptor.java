package com.ghx.app.lulu.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


import com.ghx.app.lulu.utils.DeviceUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_hx on 17/1/11.
 */
public class SessionInterceptor implements Interceptor {
    private Context mContext;

    private String versionName;
    private String deviceName;
    private String devId;
    private String osVersion;

    public SessionInterceptor(Context context) {
        mContext = context;

        versionName = getPackageVersion(context);
        deviceName = Build.DEVICE;
        devId = DeviceUtil.getDeviceId(context);

        osVersion = "android_" + Build.VERSION.SDK_INT;
    }

    private static String getPackageVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String connType = DeviceUtil.isWifiConnected(mContext) ? "WIFI" : "3G";

        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
//                .addQueryParameter("toid", String.valueOf(UserInfoManager.getUid()))
//                .addQueryParameter("token", UserInfoManager.getToken())
//                .addQueryParameter("sid", UserInfoManager.getSession())
//                .addQueryParameter("cv", BuildConfig.FLAVOR + "_" + BuildConfig.VERSION_NAME)
                .addQueryParameter("ua", deviceName)
                .addQueryParameter("dev", devId)
                .addQueryParameter("conn", connType)
                .addQueryParameter("osversion", osVersion)
                .addQueryParameter("cid", "6")
                .build();

        request = request.newBuilder().url(url).build();

        Response resp = chain.proceed(request);
        return resp;
    }
}
