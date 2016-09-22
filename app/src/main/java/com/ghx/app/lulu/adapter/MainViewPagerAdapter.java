package com.ghx.app.lulu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by guo_hx on 2016/9/22.15:56
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mList;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
