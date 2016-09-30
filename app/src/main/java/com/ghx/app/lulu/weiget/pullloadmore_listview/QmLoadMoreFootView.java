package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ghx.app.R;

/**
 * Created by mac on 16/7/29.
 */
public class QmLoadMoreFootView extends LinearLayout {

    RelativeLayout mContainer;

    public QmLoadMoreFootView(Context context) {
        super(context);
        initView(context);
    }

    public QmLoadMoreFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QmLoadMoreFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

        mContainer = (RelativeLayout) LayoutInflater.from(context).inflate(
                R.layout.item_view_load_more, null);
        ProgressWheel wheel = (ProgressWheel) mContainer.findViewById(R.id.progress_view);
        wheel.spin();
    }

    public View getContentView(){
        return mContainer;
    }
}
