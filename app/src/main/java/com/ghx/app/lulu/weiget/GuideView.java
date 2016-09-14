package com.ghx.app.lulu.weiget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ghx.app.R;
import com.ghx.app.lulu.activity.GuideActivity;
import com.ghx.app.lulu.adapter.GuideAdapter;
import com.ghx.app.lulu.anim.ZoomOutPageTransformer;
import com.ghx.app.lulu.utils.DensityUtil;

/**
 * Created by guo_hx on 2016/9/14.11:02
 * <p>
 * 如果一个page里面的布局非常复杂，那么将控件模块化，这样布局文件看起来会清晰很多
 * 但是逻辑怎么来写，我还没想到很好的方法。
 * 利用回调的方法统一在写所属的Activity或者Fragment里？
 * 但是如果布局非常非常复杂，布局抽取的层级很深，那么一层层回调又太麻烦了，逻辑会很乱，也不利已以后的维护
 * 当然可以通过广播发一个通知，Activity或者Fragment接收到这个通知后做出相应的处理，就避免掉了一层层的回调
 * <p>
 * 那么，可以在以下两种方案中选择一种
 * 1.直接将业务逻辑写在View里，这样，开发速度快，View需要什么东西都通过构造传过来，或者提供方法，传过来
 * 2.通过通知，将左右的逻辑卸载View所属的Activity或者Fragment里，View只负责UI的展示
 * <p>
 * 我觉得第二种好，但是，第一种开发速度应该是快于第二种的。我们公司项目中，处理方式大多是第一种，
 * 这里，我全部用第二种来处理，体会一下优缺点。。。
 */
public class GuideView extends RelativeLayout {

    private ViewPager mVpSplash;
    private LinearLayout mLlPointsGroup;
    private ImageView mIvFocus;
    private int mPointWhith;
    private Button mBtnSplash;

    private int[] mImageArray = {R.mipmap.welcome_01,
            R.mipmap.welcome_02,
            R.mipmap.welcome_03,
            R.mipmap.welcome_04};
    private OnButtonClickListener mListener;


    public GuideView(Context context) {
        this(context, null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_guide, this);

        mVpSplash = (ViewPager) findViewById(R.id.vp_splash);
        mLlPointsGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        mIvFocus = (ImageView) findViewById(R.id.iv_foucs);
        mBtnSplash = (Button) findViewById(R.id.btn_splash);

        initPoint(context);

        GuideAdapter splashAdapter = new GuideAdapter(((GuideActivity)getContext()).getSupportFragmentManager(), mImageArray);
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

        mBtnSplash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClick();
            }
        });

    }

    private void initPoint(Context context) {
        //根据引导界面来动态创建默认点
        for (int i = 0; i < mImageArray.length; i++) {
            ImageView point = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px
                    (context, 10), DensityUtil.dip2px(context, 10));
            //设置左边距
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(context, 10);
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

    //-----------------------------------------对外部提供的 public 方法--------------------------------------------------------

    public ViewPager getViewPager() {
        return mVpSplash;
    }

    //------------------------------------------还需要给所属的Activity或Fragment一个Button的点击回调-----------------------------------------------------------------------------

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        mListener = listener;
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }



}
