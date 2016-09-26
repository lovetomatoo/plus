package com.ghx.app.lulu.fragment.home;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.presenter.SecondFragmentPresenter;
import com.ghx.app.lulu.view.ISecondFragmentView;

/**
 * Created by guo_hx on 2016/9/22.17:13
 */

public class SecondFragment extends BaseFragment implements ISecondFragmentView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected Class getPresenter() {
        return SecondFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {
        getTopbar().hideBackBtn();
    }

    @Override
    protected void clickView(View v) {

    }
}
