/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghx.app.R;

/**
 * @author Chris
 * @time 16/7/11 下午6:10
 * @function 头部的布局
 */

public class QmHeaderView extends RelativeLayout {

    LinearLayout mContainer;
    TextView mHintTextView;
    ImageView mRefresh_img;


    public final static int STATE_STYLE1 = 1;
    public final static int STATE_STYLE2 = 2;
    public final static int STATE_STYLE3 = 3;
    public final static int STATE_STYLE4 = 4;

    private static final int RES_GIF_STYLE1 = R.mipmap.refresh_1;
    private static final int RES_GIF_STYLE2 = R.mipmap.refresh_2;
    private static final int RES_GIF_STYLE3 = R.mipmap.refresh_3;
    private static final int RES_GIF_STYLE4 = R.mipmap.refresh_4;


    public QmHeaderView(Context context) {
        super(context);
        initView(context);
    }


    public QmHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QmHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.activity_recycle_header, null);
        mHintTextView = (TextView) mContainer.findViewById(R.id.hint_text);

        mRefresh_img = (ImageView) mContainer.findViewById(R.id.img_refresh);
        /*DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res:///" + R.mipmap.refresh_2))
                .setAutoPlayAnimations(true)
                .build();
        mRefresh_img.setController(controller);*/




    }

    public void setState(int state) {



        switch (state) {
            case STATE_STYLE1:

                changeStyle(RES_GIF_STYLE1, "来嘛~");

                break;
            case STATE_STYLE2:

                changeStyle(RES_GIF_STYLE2, "嘿嘿嘿嘿～");

                break;
            case STATE_STYLE3:

                changeStyle(RES_GIF_STYLE3,"浪哩个浪～");

                break;
            case STATE_STYLE4:

                changeStyle(RES_GIF_STYLE4,"刷一刷,再扭一扭");

                break;

            default:

        }


    }


    public void changeStyle(int gifDrawable,String str){

        mHintTextView.setText(str);

       /* DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res:///" + gifDrawable))
                .setAutoPlayAnimations(true)

                .build();

        mRefresh_img.setController(controller);*/

    }

    public boolean controlState(boolean state){
        if(state){
            setVisibility(VISIBLE);
            return true;
        }else{
            setVisibility(GONE);
            return false;
        }
    }
    public View getContentView(){
        return mContainer;
    }





}
