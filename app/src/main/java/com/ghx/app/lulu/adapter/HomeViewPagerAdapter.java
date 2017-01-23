package com.ghx.app.lulu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.ghx.app.lulu.fragment.home.FourthFragment;
import com.ghx.app.lulu.fragment.home.HomeFragment;
import com.ghx.app.lulu.fragment.other.GuideFragment;
import com.ghx.app.lulu.fragment.other.HomeItemFragment;
import com.ghx.app.lulu.model.HomeViewPagerBean;
import com.ghx.app.lulu.utils.LogUtil;

import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:08
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<HomeViewPagerBean> mList;
    private String TAG = getClass().getSimpleName();

    public HomeViewPagerAdapter(FragmentManager fm, List<HomeViewPagerBean> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 0) {
            HomeItemFragment homeItemFragment = new HomeItemFragment();
            homeItemFragment.setFlag(mList.get(position).id);
            LogUtil.i_log(TAG, "new HomeItemFragment()");
            return homeItemFragment;
//        } else {
//            return new HomeItemFragment();
//        }

    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).title;
    }

}
