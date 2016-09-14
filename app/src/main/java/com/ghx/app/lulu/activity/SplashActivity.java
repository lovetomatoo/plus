package com.ghx.app.lulu.activity;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.lulu.config.Constants;
import com.ghx.app.lulu.presenter.SplashPresenter;
import com.ghx.app.lulu.utils.SPUtil;
import com.ghx.app.lulu.view.ISplashView;

/**
 * Created by guo_hx on 2016/9/14.14:32
 */
public class SplashActivity extends BaseActivity implements ISplashView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected Class getPresenter() {
        return SplashPresenter.class;
    }

    @Override
    protected void initView() {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //去SP的缓存，看是否是第一次登陆
                boolean isFirstInstall = new SPUtil(SplashActivity.this).getBoolean(Constants.SP_KEY.FIRST_INSTALL, true);

                if (isFirstInstall) {
                    to(GuideActivity.class);
                }else {
                    to(MainActivity.class);
                }
                finish();
            }
        }, 1000);
    }

    @Override
    protected void clickView(View v) {

    }
}
