package com.ghx.app.lulu.fragment.other;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.utils.ToastUtil;
import com.ghx.app.lulu.view.IHomeItemFragmentView;
import com.ghx.app.lulu.weiget.autoscroll_viewpager.AutoScrollViewPager;
import com.ghx.app.lulu.weiget.pullloadmore_listview.AnimatePullLoadMoreListView;
import com.ghx.app.lulu.weiget.pullloadmore_listview.PullLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment<HomeItemFragmentPresenter> implements IHomeItemFragmentView, PullLoadMoreListener {

    List<String> mPicUrlList = new ArrayList<>();

    private String mFlag;
    private AutoScrollViewPager mVpAuto;
    private AnimatePullLoadMoreListView mMoreListView;

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
//        mMoreListView = (AnimatePullLoadMoreListView) rootView.findViewById(R.id.more_list_view);
//        mMoreListView.setPullLoadMoreListener(this);


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
    public void onRefresh() {

        ToastUtil.showToast("onRefresh");
    }

    @Override
    public void onLoadMore() {

        ToastUtil.showToast("onLoadMore");
    }
}
