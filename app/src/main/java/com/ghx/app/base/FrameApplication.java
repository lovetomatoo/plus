package com.ghx.app.base;

import android.app.Application;

/**
 * Created by guo_hx on 2016/9/13.15:24
 *
 *
 */
public class FrameApplication extends Application {

    private static FrameApplication mAppInstance;

    public static FrameApplication getAppInstance() {

        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
    }
}
