package com.ghx.app.lulu.weiget.pullloadmore_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.ghx.app.R;

/**
 * Created by guo_hx on 16/9/30.
 */
public abstract class NewPullLoadMoreListView extends FrameLayout
        implements SuperSwipeRefreshLayout.OnPullRefreshListener, SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    FixMoveRefreshLayout mSwipeRefreshLayout;

    ListView mListView;

    PullLoadMoreListener mPullLoadMoreListener;



    QmHeaderView headerView;
    QmFooterView footerView;
    QmLoadMoreFootView overfootView;

    Context context;

    int state;


    public NewPullLoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public NewPullLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NewPullLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.new_list_pull_loadmore_layout, this);
        mSwipeRefreshLayout = (FixMoveRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setOnPullRefreshListener(this);
        mSwipeRefreshLayout.setOnPushLoadMoreListener(this);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        init();

    }

    @Override
    public void onRefresh() {

        if (mPullLoadMoreListener != null) {


            mPullLoadMoreListener.onRefresh();
        }

    }


    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onPullStatus() {

        headerView.setState((int) (1 + Math.random() * 4));
    }

    @Override
    public void onLoadMore() {

        if (mPullLoadMoreListener != null) {

            mPullLoadMoreListener.onLoadMore();
        }

    }

    @Override
    public void onPushDistance(int distance) {

    }

    @Override
    public void onPushEnable(boolean enable) {

    }

    @Override
    public void onPushStatus() {

        resertRes();
        state = (int) (1 + Math.random() * 2);
        footerView.setState(state);
        //startAnimation();

    }


    @Override
    public void startUpAnimation(int distance) {

        beginUpAnimation(distance);
    }



    public void setPullLoadMoreListener(PullLoadMoreListener listener) {

        this.mPullLoadMoreListener = listener;
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








    public ListView getListView() {

        return mListView;
    }




    public void init() {

        headerView = new QmHeaderView(context);
        footerView = new QmFooterView(context);
        overfootView = new QmLoadMoreFootView(context);
        mSwipeRefreshLayout.setHeaderView(headerView.getContentView());

        showEndAnimation(true);
    }

    public void setHasMore(boolean hasMore) {

        mSwipeRefreshLayout.setLoadMore(hasMore);
    }

    public void setRefresh(boolean refresh) {

        mSwipeRefreshLayout.setLoadMore(refresh);
    }

    public boolean isHasMore() {

        return mSwipeRefreshLayout.isLoadMore();
    }

    /**
     * @author Chris
     * @time 16/7/15 下午2:51
     * @function 包含下啦上啦的逻辑
     */
    public void stopAnim() {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mSwipeRefreshLayout.isLoadMore()) {
            mSwipeRefreshLayout.setLoadMore(false);
        }

    }

    public abstract void beginUpAnimation(int distance);



    public abstract void resertRes();


    /**
     * @author Chris
     * @time 16/7/29 下午2:00
     * @function  区分是最后一页还不是最后一页的加载
     */
    public void showEndAnimation(boolean isOver) {

        mSwipeRefreshLayout.setMoveOver(isOver);
        if (isOver)
        {
            mSwipeRefreshLayout.setFooterView(footerView.getContentView());

        }



    }


}
