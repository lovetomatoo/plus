package com.ghx.app.lulu.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by guo_hx on 2016/9/28.17:50
 */

public class HomeLunbotuAdapter extends PagerAdapter {

    private List<View> mViewList;

    public HomeLunbotuAdapter(List<View> list) {
        this.mViewList = list;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
        ((ViewPager) container).addView(mViewList.get(position), 0);

        return mViewList.get(position);
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(mViewList.get(position));
    }
}
