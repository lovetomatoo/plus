package com.ghx.app.lulu.weiget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by guo_hx on 2016/9/22.19:17
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);//将触摸事件传递给viewpager，实现了左右滑动的效果
        return false;//事件不处理，viewpager就响应不到触摸事件，左右活动效果就失效了
    }

    //    事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return false;//如果子控件中还有viewpager或者listview，这种能够响应触摸事件的控件，那我们必须保证它们能够正常的响应
    }
}
