package com.ghx.app.lulu.utils;

import android.content.Context;
import android.util.Log;

import com.ghx.app.BuildConfig;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.base.FrameApplication;

/**
 * Created by guo_hx on 2016/9/27.14:39
 */

public class LogUtil {

    public static void i_log(String tag, String str) {
        if (FrameApplication.isDebug) {
            Log.i(tag, str);
        }
    }

    public static void d_log(String tag, String str) {
        if (FrameApplication.isDebug) {
            Log.d(tag, str);
        }
    }

    public static void w_log(String tag, String str) {
        if (FrameApplication.isDebug) {
            Log.w(tag, str);
        }
    }

    public static void e_log(String tag, String str) {
        if (FrameApplication.isDebug) {
            Log.e(tag, str);
        }
    }

    public static void v_log(String tag, String str) {
        if (FrameApplication.isDebug) {
            Log.v(tag, str);
        }
    }
}
