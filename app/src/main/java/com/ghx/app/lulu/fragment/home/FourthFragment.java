package com.ghx.app.lulu.fragment.home;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.presenter.FourthFragmentPresenter;

/**
 * Created by guo_hx on 2016/9/22.17:18
 */

public class FourthFragment extends BaseFragment implements IBaseView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fourth;
    }

    @Override
    protected Class getPresenter() {
        return FourthFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {
        getTopbar().hideBackBtn();
    }
}
