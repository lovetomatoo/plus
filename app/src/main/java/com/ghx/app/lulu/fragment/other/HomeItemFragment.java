package com.ghx.app.lulu.fragment.other;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment implements IHomeItemFragmentView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_item;
    }

    @Override
    protected Class getPresenter() {
        return HomeItemFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {

    }

    @Override
    protected void clickView(View v) {

    }
}
