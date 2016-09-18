package com.ghx.app.lulu.activity;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.lulu.presenter.MainPresenter;
import com.ghx.app.lulu.view.IMainView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView {


    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected Class getPresenter() {
        return MainPresenter.class;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void clickView(View v) {

    }

}
