package com.ghx.app.lulu.fragment.home;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.adapter.HomeViewPagerAdapter;
import com.ghx.app.lulu.fragment.other.HomeItemFragment;
import com.ghx.app.lulu.model.HomeViewPagerBean;
import com.ghx.app.lulu.presenter.HomeFragmentPresenter;
import com.ghx.app.lulu.utils.LogUtil;
import com.ghx.app.lulu.view.IHomeFragmentView;
import com.ghx.app.lulu.weiget.Topbar;
import com.ghx.app.lulu.weiget.pullloadmore_recyleview.PullLoadMoreRecyclerView;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/22.16:09
 */

public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements IHomeFragmentView, Topbar.On2ClickListener {

    private String TAG = getClass().getSimpleName();

    private List<HomeViewPagerBean> mList = new ArrayList<>();

    private ViewPager mViewPagerHome;
    private TabLayout mTabLayoutHome;

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

        Topbar topbar = getTopbar();
        topbar.hideBackBtn();


        mViewPagerHome = (ViewPager) rootView.findViewById(R.id.viewpager_home);
        mTabLayoutHome = (TabLayout) rootView.findViewById(R.id.tablayout_home);

        topbar.setOnTopbar2ClickListener(this);

        mTabLayoutHome.setTabMode(TabLayout.MODE_FIXED);
        LogUtil.i_log(TAG, "HomeFragment_initAllWidget");
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void showList(List<HomeViewPagerBean> list) {

        mList.clear();
        mList.addAll(list);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), mList);
        mViewPagerHome.setAdapter(homeViewPagerAdapter);
        mViewPagerHome.setOffscreenPageLimit(100);

        mTabLayoutHome.setupWithViewPager(mViewPagerHome);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i_log(TAG, "HomeFragment_onDestroy");
    }

    @Override
    public void setOn2ClickListener() {

        int currentItem = mViewPagerHome.getCurrentItem();
        HomeViewPagerAdapter adapter = (HomeViewPagerAdapter) mViewPagerHome.getAdapter();
        HomeItemFragment homeItemFragment = (HomeItemFragment) adapter.getItem(currentItem);
        PullLoadMoreRecyclerView pullLoadMoreRecyclerView = homeItemFragment.getmRvPullLoadMore();
        pullLoadMoreRecyclerView .scrollToTop();
    }
}
