package com.ghx.app.lulu.fragment.other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.adapter.HomeItemRecylerViewAdapter;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.view.IHomeItemFragmentView;
import com.ghx.app.lulu.weiget.autoscroll_viewpager.AutoScrollViewPager;
import com.ghx.app.lulu.weiget.pullloadmore_recyleview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment<HomeItemFragmentPresenter> implements IHomeItemFragmentView, PullLoadMoreRecyclerView.PullLoadMoreListener {

    List<String> mPicUrlList = new ArrayList<>();

    private String mFlag;
    private AutoScrollViewPager mVpAuto;
    private PullLoadMoreRecyclerView mRvPunnLoadMore;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_item;
    }

    @Override
    protected Class<HomeItemFragmentPresenter> getPresenter() {
        return HomeItemFragmentPresenter.class;
    }

    @Override
    protected void initAllWidget(View rootView) {

        mVpAuto = (AutoScrollViewPager) rootView.findViewById(R.id.vp_auto);

        mRvPunnLoadMore = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.rv_pull_load_more);
        mRvPunnLoadMore.setGridLayout(2);
        mPresenter.getItemServerData();
//        //显示下拉刷新
        mRvPunnLoadMore.setRefreshing(true);
//        //设置上拉刷新文字
//        mRvPunnLoadMore.setFooterViewText("loading");
//        //设置上拉刷新文字颜色
//        mRvPunnLoadMore.setFooterViewTextColor(R.color.white_main);
//        //设置加载更多背景色
//        mRvPunnLoadMore.setFooterViewBackgroundColor(R.color.black_main);
//        mRvPunnLoadMore.setLinearLayout();
//
//        mRvPunnLoadMore.setOnPullLoadMoreListener(this);
//        //setEmptyView，演示空数据，可以提示“数据加载中”
//        mRvPunnLoadMore.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null));
////        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
////        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
////        getData();
    }

    @Override
    protected void clickView(View v) {

    }

    public void setFlag(String flag) {

        mFlag = flag;
    }

    @Override
    public void showAds(LunbotuBean response) {
        List<LunbotuBean.LunbotuItemBean> data = response.data;
        mVpAuto.setPhotoData(data);
        mVpAuto.setBorderAnimation(false);
    }

    @Override
    public void showItem(HomeItemRvItemModel body) {

        HomeItemRecylerViewAdapter homeItemRecylerViewAdapter = new HomeItemRecylerViewAdapter(getActivity(), body.data);
        mRvPunnLoadMore.setAdapter(homeItemRecylerViewAdapter);
        mRvPunnLoadMore.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mRvPunnLoadMore.setRefreshing(true);
        mPresenter.getItemServerData();
    }

    @Override
    public void onLoadMore() {
        mPresenter.getItemServerData();
    }
}
