package com.ghx.app.lulu.anim;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by guo_hx on 2016/9/13.15:18
 * 用到别的动画的时候，可以模仿着谷歌提供的那两个类来写
 */
public class GHXTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {

        //看了下Google提供的两个类，都是分三种情况来处理的

        if (position < -1) {//1.

        }else if (position <= 1) {

        }else {

        }

    }
}
