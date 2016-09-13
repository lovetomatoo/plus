package com.ghx.app.lulu.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.adapter.SplashAdapter;
import com.ghx.app.lulu.anim.ZoomOutPageTransformer;
import com.ghx.app.lulu.presenter.SplashPresenter;
import com.ghx.app.lulu.utils.DensityUtil;

/**
 * Created by guo_hx on 2016/9/12.17:11
 */
public class GuideActivity extends BaseActivity implements IBaseView {


    private int[] mImageArray = {R.mipmap.welcome_01,
            R.mipmap.welcome_02,
            R.mipmap.welcome_03,
            R.mipmap.welcome_04};

    private ViewPager mVpSplash;
    private LinearLayout mLlPointsGroup;
    private ImageView mIvFocus;
    private int mPointWhith;
    private Button mBtnSplash;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected Class getPresenter() {
        return SplashPresenter.class;
    }

    @Override
    protected void initView() {
        mVpSplash = (ViewPager) findViewById(R.id.vp_splash);
        mLlPointsGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        mIvFocus = (ImageView) findViewById(R.id.iv_foucs);
        mBtnSplash = (Button) findViewById(R.id.btn_splash);

        initPoint();

        SplashAdapter splashAdapter = new SplashAdapter(getSupportFragmentManager(), mImageArray);
        mVpSplash.setAdapter(splashAdapter);
        mVpSplash.setPageTransformer(true, new ZoomOutPageTransformer());
//        mVpSplash.setPageTransformer(false, new DepthPageTransformer());

        mVpSplash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当手指滑动的时候，实时设置选中点红点的x轴的平移
                //int translationX = （position+offset）*间距
                float traslationX = (position + positionOffset)*mPointWhith;
                mIvFocus.setTranslationX(traslationX);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageArray.length - 1) {
                    mBtnSplash.setVisibility(View.VISIBLE);
                    mLlPointsGroup.setVisibility(View.GONE);
                }else {
                    mBtnSplash.setVisibility(View.GONE);
                    mLlPointsGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        //根据引导界面来动态创建默认点
        for (int i = 0; i < mImageArray.length; i++) {
            ImageView point = new ImageView(GuideActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px
                    (GuideActivity.this, 10), DensityUtil.dip2px(GuideActivity.this, 10));
            //设置左边距
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(GuideActivity.this, 10);
            }
            point.setLayoutParams(params);
            point.setImageResource(R.drawable.shape_dot_normal);
            mLlPointsGroup.addView(point);

            mIvFocus.postDelayed(new Runnable() {


                @Override
                public void run() {
                    //计算点之间的距离
                    mPointWhith = mLlPointsGroup.getChildAt(1).getLeft() - mLlPointsGroup
                            .getChildAt(0).getLeft();
                    System.out.println("point_width:" + mPointWhith);
                }
            }, 20);
        }

    }

    @Override
    public void onBackPressed() {
        if (mVpSplash.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mVpSplash.setCurrentItem(mVpSplash.getCurrentItem() - 1);
        }
    }


}
