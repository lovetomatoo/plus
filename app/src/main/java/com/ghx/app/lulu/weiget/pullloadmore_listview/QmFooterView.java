package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ghx.app.R;

/**
 * Created by mac on 16/7/12.
 */
public class QmFooterView extends LinearLayout {

    LinearLayout mContainer;

    ImageView mRefresh_img;



    public final static int STATE_STYLE1 = 1;
    public final static int STATE_STYLE2 = 2;



    private static final int LOAD_LOAD_ANIM_1 = R.drawable.loading_1;

    private static final int LOAD_LOAD_ANIM_2 = R.drawable.loading_2;


    AnimationDrawable anim;

    public QmFooterView(Context context) {
        super(context);
        initView(context);
    }

    public QmFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QmFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.activity_recycle_footer, null);

        mRefresh_img = (ImageView) mContainer.findViewById(R.id.img_loading);




    }

    public void setState(int state) {


        switch (state) {
            case STATE_STYLE1:

                changeStyle(LOAD_LOAD_ANIM_1);

                break;
            case STATE_STYLE2:

                changeStyle(LOAD_LOAD_ANIM_2);

                break;


        }


    }

    public void changeStyle(int gifDrawable) {

        mRefresh_img.setBackgroundResource(gifDrawable);

    }


    /**
     * @author Chris
     * @time 16/7/12 下午8:18
     * @function 有数据隐藏
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = 0;
        mContainer.setLayoutParams(lp);
    }

    public void show() {
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mContainer.setLayoutParams(lp);
    }

    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.bottomMargin = height;
        mContainer.setLayoutParams(lp);
    }

    public View getContentView() {

        return mContainer;
    }

    public ImageView getAnimationView() {

        return mRefresh_img;
    }



}
