package com.ghx.app.lulu.fragment.other;

import android.view.View;
import android.widget.TextView;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment<HomeItemFragmentPresenter> implements IHomeItemFragmentView {

    private String mFlag;
    private TextView mTvHomeViewPagerItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_item;
    }

    @Override
    protected Class<HomeItemFragmentPresenter> getPresenter() {
        return HomeItemFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {

        mTvHomeViewPagerItem = (TextView) rootView.findViewById(R.id.tv_home_viewpager_item);
        mTvHomeViewPagerItem.setText(mFlag);

//        mPresenter.getServerData(mFlag);
        mPresenter.getServerData(mFlag);

    }

    @Override
    protected void clickView(View v) {

    }

    public void setFlag(String flag) {

        mFlag = flag;
    }
}
