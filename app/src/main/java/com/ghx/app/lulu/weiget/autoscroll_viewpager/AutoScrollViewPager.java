package com.ghx.app.lulu.weiget.autoscroll_viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.utils.ImageLoadUtil;
import com.ghx.app.lulu.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/28.19:30
 * 这个类用法简单至极 没啥东西 直接ViewInject  一下
 * setData一下就行了 就可以自动滚动了
 * 但是如果还需要脚注控件
 */

public class AutoScrollViewPager extends ViewPager {

    //默认的滚动时间
    public static final int DEFAULT_INTERVAL = 1000;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    /** do nothing when sliding at the last or first item **/
    public static final int SLIDE_BORDER_MODE_NONE = 0;
    /** cycle when sliding at the last or first item **/
    public static final int SLIDE_BORDER_MODE_CYCLE = 1;
    /** deliver event to parent when sliding at the last or first item **/
    public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;

    /** auto scroll time in milliseconds, default is {@link #DEFAULT_INTERVAL} **/
    private long interval = DEFAULT_INTERVAL;
    /** auto scroll direction, default is {@link #RIGHT} **/
    private int direction = RIGHT;
    /**
     * whether automatic cycle when auto scroll reaching the last or first item,
     * default is true
     **/
    private boolean isCycle = true;
    /** whether stop auto scroll when touching, default is true **/
    private boolean stopScrollWhenTouch = true;
    /**
     * how to process when sliding at the last or first item, default is
     * {@link #SLIDE_BORDER_MODE_NONE}
     **/
    private int slideBorderMode = SLIDE_BORDER_MODE_NONE;
    /** whether animating when auto scroll at the last or first item **/
    private boolean isBorderAnimation = true;

    private Handler handler;
    private boolean isAutoScroll = true;
    private boolean isStopByTouch = false;
    private float touchX = 0f, downX = 0f;
    private CustomDurationScroller scroller = null;

    public static final int SCROLL_WHAT = 0;
    private Context context;

    public AutoScrollViewPager(Context paramContext) {
        super(paramContext);
        context=paramContext;
        init();
    }

    public AutoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        context=paramContext;
        init();
    }

    private void init() {

        setViewPagerScroller();
        handler = new MyHandler();
    }

    /**
     * start auto scroll, first scroll delay time is {@link #getInterval()}
     */
    public void startAutoScroll() {
        isAutoScroll = true;
        sendScrollMessage(interval);
    }

    /**
     * start auto scroll
     *
     * @param delayTimeInMills
     *            first scroll delay time
     */
    public void startAutoScroll(int delayTimeInMills) {
        isAutoScroll = true;
        sendScrollMessage(delayTimeInMills);
    }

    /**
     * stop auto scroll
     */
    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(SCROLL_WHAT);
    }

    /**
     * 修改自动滚动的倍率 注意不是ms
     */
    public void setScrollDurationFactor(double scrollFactor) {
        scroller.setScrollDurationFactor(scrollFactor);
    }

    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        handler.removeMessages(SCROLL_WHAT);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }

    /**
     * set ViewPager scroller to change animation duration when sliding
     */
    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorField.setAccessible(true);

            scroller = new CustomDurationScroller(getContext(), (Interpolator) interpolatorField.get(null));
            scrollerField.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * scroll only once
     */
    public void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int totalCount;
        if (adapter == null || (totalCount = adapter.getCount()) <= 1) {
            return;
        }

        int nextItem = (direction == LEFT) ? --currentItem : ++currentItem;
        if (nextItem < 0) {
            if (isCycle) {
                setCurrentItem(totalCount - 1, isBorderAnimation);
            }
        } else if (nextItem == totalCount) {
            if (isCycle) {
                setCurrentItem(0, isBorderAnimation);
            }
        } else {
            setCurrentItem(nextItem, true);
        }
    }

    /**
     * <ul>
     * if stopScrollWhenTouch is true
     * <li>if event is down, stop auto scroll.</li>
     * <li>if event is up, start auto scroll again.</li>
     * </ul>
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (stopScrollWhenTouch) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN && isAutoScroll) {
                isStopByTouch = true;
                stopAutoScroll();
            } else if (ev.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
                startAutoScroll();
            }
        }

        if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT || slideBorderMode == SLIDE_BORDER_MODE_CYCLE) {
            touchX = ev.getX();
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                downX = touchX;
            }
            int currentItem = getCurrentItem();
            PagerAdapter adapter = getAdapter();
            int pageCount = adapter == null ? 0 : adapter.getCount();
            /**
             * current index is first one and slide to right or current index is
             * last one and slide to left.<br/>
             * if slide border mode is to parent, then
             * requestDisallowInterceptTouchEvent false.<br/>
             * else scroll to last one when current item is first one, scroll to
             * first one when current item is last one.
             */
            if ((currentItem == 0 && downX <= touchX) || (currentItem == pageCount - 1 && downX >= touchX)) {
                if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    if (pageCount > 1) {
                        setCurrentItem(pageCount - currentItem - 1, isBorderAnimation);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return super.onTouchEvent(ev);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT :
                    scrollOnce();
                    sendScrollMessage(interval);
                default :
                    break;
            }
        }
    }

    /**
     * get auto scroll time in milliseconds, default is
     * {@link #DEFAULT_INTERVAL}
     *
     * @return the interval
     */
    public long getInterval() {
        return interval;
    }

    /**
     * 设置自动滚动时间以毫秒为单位,
     * {@link #DEFAULT_INTERVAL}
     *
     * @param interval
     *            the interval to set
     */
    public void setInterval(long interval) {
        this.interval = interval;
    }

    /**
     * get auto scroll direction
     *
     * @return {@link #LEFT} or {@link #RIGHT}, default is {@link #RIGHT}
     */
    public int getDirection() {
        return (direction == LEFT) ? LEFT : RIGHT;
    }

    /**
     * 设置自动滚动方向
     * @param direction
     *            {@link #LEFT} or {@link #RIGHT}, default is {@link #RIGHT}
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * 是否自动循环自动滚动到最后一个或第一项时,
     * default is true
     *
     * @return the isCycle
     */
    public boolean isCycle() {
        return isCycle;
    }

    /**
     * 设置是否自动循环时,自动滚动到最后还是第一次
     * 默认 true  即为第一个
     *
     * @param isCycle
     *            the isCycle to set
     */
    public void setCycle(boolean isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 是否停止控件触摸时的滚动
     *
     * @return the stopScrollWhenTouch
     */
    public boolean isStopScrollWhenTouch() {
        return stopScrollWhenTouch;
    }

    /**
     * 是否停止控件滚动时的触摸
     * 默认是true
     *
     * @param stopScrollWhenTouch
     */
    public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
        this.stopScrollWhenTouch = stopScrollWhenTouch;
    }

    /**
     * 如何处理当滑动在最后一项时的情况
     *
     * @return the slideBorderMode {@link #SLIDE_BORDER_MODE_NONE},
     *         {@link #SLIDE_BORDER_MODE_TO_PARENT},
     *         {@link #SLIDE_BORDER_MODE_CYCLE}, default is
     *         {@link #SLIDE_BORDER_MODE_NONE}
     */
    public int getSlideBorderMode() {
        return slideBorderMode;
    }

    /**
     * 设置滑动在最后时如何处理或第一项
     *
     * @param slideBorderMode
     *            {@link #SLIDE_BORDER_MODE_NONE},
     *            {@link #SLIDE_BORDER_MODE_TO_PARENT},
     *            {@link #SLIDE_BORDER_MODE_CYCLE}, default is
     *            {@link #SLIDE_BORDER_MODE_NONE}
     */
    public void setSlideBorderMode(int slideBorderMode) {
        this.slideBorderMode = slideBorderMode;
    }

    /**
     * 动画时是否自动滚动在最后或第一项,
     * 默认 true
     *
     * @return
     */
    public boolean isBorderAnimation() {
        return isBorderAnimation;
    }

    /**
     * 设置动画时是否自动滚动在最后或第一项
     * 默认true
     * 为true的时候 循环滚动 而不是反向回到最前面的一个view
     *
     * @param isBorderAnimation
     */
    public void setBorderAnimation(boolean isBorderAnimation) {
        this.isBorderAnimation = isBorderAnimation;
    }

    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;
        public AdvAdapter(List<View> views) {
            this.views = views;
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }
        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void startUpdate(View arg0) {
        }
    }


    /**
     * 设置数据
     * @param photolist
     */
    public void setPhotoData(final List<LunbotuBean.LunbotuItemBean> photolist) {
        if (photolist == null || photolist.size() == 0)
            return;
        List<View> advPics = new ArrayList<View>();
        for (int i = 0; i < photolist.size(); i++) {
            String url = photolist.get(i).pic_url;

            ImageView img = new ImageView(context);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(param);
            ImageLoadUtil.LoadImage(context, url, img);

            //点击跳转
            final int finalI = i;
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO......
                    ToastUtil.showToast(finalI +"");
                }
            });
            advPics.add(img);
        }
        AutoScrollViewPager.this.setAdapter(new AdvAdapter(advPics));

        //修改滚动的倍率
        AutoScrollViewPager.this.setScrollDurationFactor(1.8D);
        //这里设置的是 viewpager 屏幕外保存的 view 的个数
        AutoScrollViewPager.this.setOffscreenPageLimit(2);
        //循环滚动 而不是反向回到最前面的一个view
        AutoScrollViewPager.this.setBorderAnimation(true);

        AutoScrollViewPager.this.setInterval(6000);

        //开始滚动
        AutoScrollViewPager.this.startAutoScroll();

    }
}

