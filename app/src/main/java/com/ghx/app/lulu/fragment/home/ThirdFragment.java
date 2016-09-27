package com.ghx.app.lulu.fragment.home;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.presenter.ThirdFragmentPresenter;
import com.ghx.app.lulu.view.IThirdFragmentView;

/**
 * Created by guo_hx on 2016/9/22.17:18
 */

public class ThirdFragment extends BaseFragment<ThirdFragmentPresenter> implements IThirdFragmentView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_third;
    }

    @Override
    protected Class getPresenter() {
        return ThirdFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {
        getTopbar().hideBackBtn();
    }

    @Override
    protected void clickView(View v) {

    }
}
