package com.ghx.app.lulu.fragment.home;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.adapter.HomeViewPagerAdapter;
import com.ghx.app.lulu.fragment.other.HomeItemFragment;
import com.ghx.app.lulu.presenter.HomeFragmentPresenter;
import com.ghx.app.lulu.view.IHomeFragmentView;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/22.16:09
 */

public class HomeFragment extends BaseFragment implements IHomeFragmentView {

    private ViewPager mViewPagerHome;
    private TabLayout mTabLayoutHome;

    private List<Fragment> mList = new ArrayList<>();

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

        initFragment();

        mViewPagerHome = (ViewPager) rootView.findViewById(R.id.viewpager_home);
        mTabLayoutHome = (TabLayout) rootView.findViewById(R.id.tablayout_home);

        mTabLayoutHome.setupWithViewPager(mViewPagerHome);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getFragmentManager(), mList);
        mViewPagerHome.setAdapter(homeViewPagerAdapter);
    }

    private void initFragment() {
        HomeItemFragment homeItemFragment1 = new HomeItemFragment();
        HomeItemFragment homeItemFragment2 = new HomeItemFragment();
        HomeItemFragment homeItemFragment3 = new HomeItemFragment();

        mList.add(homeItemFragment1);
        mList.add(homeItemFragment2);
        mList.add(homeItemFragment3);

    }

    @Override
    protected void clickView(View v) {

    }
}
