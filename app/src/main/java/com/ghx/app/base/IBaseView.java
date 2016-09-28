package com.ghx.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.ghx.app.lulu.model.HomeViewPagerBean;
import com.ghx.app.lulu.model.LunbotuBean;

import java.util.List;

import retrofit2.Response;

/**
 * Created by guo_hx on 2016/9/12.17:15
 */
public interface IBaseView {

    Handler getHandler();

    Activity getActivity();

    void toast(String str_msg);

    void to(Class<?> cls, Bundle bundles);

}
