package com.ghx.app.lulu.activity;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.base.BasePresenter;
import com.ghx.app.lulu.presenter.MainPresenter;
import com.ghx.app.lulu.view.IMainView;

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


}
