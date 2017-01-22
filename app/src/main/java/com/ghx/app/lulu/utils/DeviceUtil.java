package com.ghx.app.lulu.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;

import com.ghx.app.base.FrameApplication;

import java.io.File;


/**
 * Created by guo_hx on 17/1/10.
 */
public final class DeviceUtil {
    private static final String TAG = "DeviceUtil";

    private DeviceUtil() { /*DISABLE*/ }

    public static String getCacheDir(Context context) {
        final Context appCtx = FrameApplication.getAppInstance();
        String path = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            try {
                final File dir = appCtx.getExternalCacheDir();
                if (null != dir) {
                    path = dir.getPath();
                }
            } catch (Throwable ignored) {
            }
        }
        if (null == path) {
            try {
                final File dir = appCtx.getCacheDir();
                if (null != dir) {
                    path = dir.getPath();
                }
            } catch (Throwable ignored) {
            }
        }
        return path;
    }

    public static String getDataDir(Context context) {
        String filePath = null;
        filePath = context.getApplicationInfo().dataDir;

        return filePath;
    }

    public static String getStorageDir(Context context) {
        String filePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            try {
                filePath = Environment.getExternalStorageDirectory().getPath();
            } catch (Exception ignored) {
            }
        } else {
            filePath = context.getFilesDir().getPath();
        }
        return filePath;
    }

    public static boolean hasActiveNetwork(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }

        return activeNetworkInfo.isAvailable()
                && activeNetworkInfo.isConnected();
    }


    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return mWifi.isConnected();
    }

    public static int dip2px(Resources r, float dpValue) {
        final float scale = r.getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Resources r, float pxValue) {
        final float scale = r.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int swap(int value) {
        int b1 = (value >> 0) & 0xff;
        int b2 = (value >> 8) & 0xff;
        int b3 = (value >> 16) & 0xff;
        int b4 = (value >> 24) & 0xff;

        return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
    }

    public static final String getDeviceId(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // md5 (DEFAULT_DEVICE_ID + slat)
    private static final String DEFAULT_DEVICE_ID_SIGNATURE =
            "d20015821573362f92fdce3cdfb9a232".toLowerCase();



}
