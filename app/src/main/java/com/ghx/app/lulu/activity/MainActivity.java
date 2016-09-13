package com.ghx.app.lulu.activity;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.base.BasePresenter;
import com.ghx.app.lulu.presenter.SplashPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected Class<BasePresenter> getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

}
