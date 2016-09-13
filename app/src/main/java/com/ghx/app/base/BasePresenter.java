package com.ghx.app.base;

import android.os.Bundle;
import android.os.Message;

/**
 * Created by guo_hx on 2016/9/12.16:31
 */
public abstract class BasePresenter {

    public abstract void handleMsg(Message msg);

    public abstract void initData(Bundle savedInstanceState);

    public void viewShow() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

}
