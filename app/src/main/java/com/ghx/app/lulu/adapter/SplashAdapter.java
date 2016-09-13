package com.ghx.app.lulu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ghx.app.lulu.fragment.SplashFragment;

/**
 * Created by guo_hx on 2016/9/13.10:47
 */
public class SplashAdapter extends FragmentStatePagerAdapter {


    private int[] mArray;

    public SplashAdapter(FragmentManager fm, int[] count) {
        super(fm);
        this.mArray = count;
    }

    @Override
    public Fragment getItem(int position) {
        return SplashFragment.creatInstance(mArray, position);
    }

    @Override
    public int getCount() {
        return mArray.length;
    }
}
