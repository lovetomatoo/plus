package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mac on 16/8/2.
 */
public class FixMoveRefreshLayout extends SuperSwipeRefreshLayout {


    public FixMoveRefreshLayout(Context context) {
        super(context);
    }

    public FixMoveRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 用于判断是横向滑动还是纵向滑动的 case  避免横滑成为竖滑
     *
     * @param ev
     * @return
     */
    private float xDistance, yDistance, xLast, yLast;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
