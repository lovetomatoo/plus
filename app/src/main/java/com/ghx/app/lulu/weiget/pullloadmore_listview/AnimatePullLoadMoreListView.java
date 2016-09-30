package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.ghx.app.lulu.utils.DensityUtil;

/**
 * @author Chris
 * @time 16/7/18 下午12:07
 * @function 主要负责动画的暂停和播放的逻辑
 */
public class AnimatePullLoadMoreListView extends NewPullLoadMoreListView {

    Drawable stopDrawable;

    int stopFrame;

    AnimationDrawable LoadMoreAnim;

    private static final int DUR_TIME = 50;

    private static final int MAXDISTANCE = DensityUtil.dip2px(40);


    private int framePix;

    private AnimationDrawable LoadAllAnim;
    private Slop slop;


    public AnimatePullLoadMoreListView(Context context) {
        super(context);
        initView();
    }

    public AnimatePullLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AnimatePullLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {

        slop = new Slop();
    }

    @Override
    public void beginUpAnimation(int distance) {

        if (LoadAllAnim == null) {
            LoadAllAnim = (AnimationDrawable) footerView.getAnimationView().getBackground();

            framePix = (MAXDISTANCE / LoadAllAnim.getNumberOfFrames());
        }

        //每移动多少像素移动一帧


        if (distance > DensityUtil.dip2px(85)) {

            int numofames = (distance - DensityUtil.dip2px(85)) / framePix;

            if (numofames > LoadAllAnim.getNumberOfFrames()) {

                numofames = LoadAllAnim.getNumberOfFrames();
            }


            if (slop.getNumofames() != 0) {

                //顺时针
                if (numofames - slop.getNumofames() > 0) {


                    LoadMoreAnim = new AnimationDrawable();

                    for (int i = slop.getNumofames(); i < numofames; i++) {


                        if (i < LoadAllAnim.getNumberOfFrames()) {

                            LoadMoreAnim.addFrame(LoadAllAnim.getFrame(i), DUR_TIME);
                        }

                    }

                    footerView.getAnimationView().setBackgroundDrawable(LoadMoreAnim);

                    if (LoadMoreAnim != null) {
                        LoadMoreAnim.start();

                    }


                }
            }


            //停止的逻辑,纪录位置
            if (numofames - slop.getNumofames() == 0) {

                if (LoadMoreAnim != null) {
                    if (LoadMoreAnim.isRunning()) {
                        LoadMoreAnim.stop();
                    }
                }

            }


            //逆时针
            if (numofames - slop.getNumofames() < 0) {
                LoadMoreAnim = new AnimationDrawable();

                for (int i = slop.getNumofames()-1; i >= numofames; i--) {

                    LoadMoreAnim.addFrame(LoadAllAnim.getFrame(i), DUR_TIME);
                }

                footerView.getAnimationView().setBackgroundDrawable(LoadMoreAnim);

                if (LoadMoreAnim != null) {
                    LoadMoreAnim.start();

                }


            }


            slop.setNumofames(numofames);
        }
    }



    @Override
    public void resertRes() {

        recycle();

    }

    /**
     * @author Chris
     * @time 16/7/18 下午1:00
     * @function
     */
    public void recycle() {

        stopFrame = 0;

        if (stopDrawable != null) {

            stopDrawable = null;
        }


        LoadMoreAnim = null;
        LoadAllAnim = null;

        slop.setNumofames(0);


    }

    private class Slop {

        private int numofames = 0;

        public int getNumofames() {

            return numofames;
        }

        public void setNumofames(int numofames) {

            this.numofames = numofames;
        }
    }


}
