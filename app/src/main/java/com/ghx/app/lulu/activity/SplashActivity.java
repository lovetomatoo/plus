package com.ghx.app.lulu.activity;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.adapter.SplashAdapter;
import com.ghx.app.lulu.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/12.17:11
 */
public class SplashActivity extends BaseActivity implements IBaseView {

    private List<View> mList = new ArrayList<>();
    private int[] imageArray = {R.mipmap.welcome_01,
            R.mipmap.welcome_02,
            R.mipmap.welcome_03,
            R.mipmap.welcome_04};

    private ViewPager mVpSplash;

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
        mVpSplash = (ViewPager) findViewById(R.id.vp_splash);

        initAdapterView();
        SplashAdapter splashAdapter = new SplashAdapter(mList);
        mVpSplash.setAdapter(splashAdapter);
    }

    private void initAdapterView() {
        for (int i = 0; i < imageArray.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.adapter_splash, null);
            ImageView ivSplash = (ImageView) view.findViewById(R.id.iv_splash);
            ivSplash.setImageResource(imageArray[i]);
            mList.add(view);
        }

    }
}
