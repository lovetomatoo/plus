package com.ghx.app.lulu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ghx.app.lulu.fragment.GuideFragment;

/**
 * Created by guo_hx on 2016/9/13.10:47
 */
public class GuideAdapter extends FragmentStatePagerAdapter {


    private int[] mArray;

    public GuideAdapter(FragmentManager fm, int[] count) {
        super(fm);
        this.mArray = count;
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.creatInstance(mArray, position);
    }

    @Override
    public int getCount() {
        return mArray.length;
    }
}
