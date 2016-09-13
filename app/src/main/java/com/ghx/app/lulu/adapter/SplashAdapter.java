package com.ghx.app.lulu.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/13.10:47
 */
public class SplashAdapter extends PagerAdapter {

    private List<View> mList;

    public SplashAdapter(List<View> list) {

        mList = list;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager) container).addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager)container).removeView(mList.get(position));
    }
}
