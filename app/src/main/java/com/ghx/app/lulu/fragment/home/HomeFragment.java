package com.ghx.app.lulu.fragment.home;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.presenter.HomeFragmentPresenter;

import android.view.View;

/**
 * Created by guo_hx on 2016/9/22.16:09
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class getPresenter() {
        return HomeFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {
        getTopbar().hideBackBtn();
    }
}
