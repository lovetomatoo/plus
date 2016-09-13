package com.ghx.app.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by guo_hx on 2016/9/12.16:16
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private BasePresenter mPresenter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isFinishing()) {
                handleMsg(msg);
            }
        }
    };

    protected void handleMsg(Message msg) {
        mPresenter.handleMsg(msg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //状态栏和ActivonBar处理，现在写在Base里，后面用不到的话，再移出去
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int options =
                    //这个属性，加上以后，状态栏会消失，可以在状态栏区域下滑，将状态栏划出来，
//                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(options);
            //设置状态栏的_背景颜色_为透明__实现所谓的沉浸式状态栏
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {
            mPresenter = (BasePresenter) getPresenter().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mPresenter.initData(savedInstanceState);

        initView();

        mPresenter.viewShow();
    }

   /* @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
*/

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract Class getPresenter();

    protected abstract void initView();

    @Override
    public Handler getHandler() {
        return mHandler;
    }
}
