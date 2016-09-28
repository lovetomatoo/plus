package com.ghx.app.lulu.fragment.other;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.adapter.HomeLunbotuAdapter;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.utils.ImageLoadUtil;
import com.ghx.app.lulu.view.IHomeItemFragmentView;
import com.ghx.app.lulu.weiget.autoscroll_viewpager.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment<HomeItemFragmentPresenter> implements IHomeItemFragmentView {

    List<View> mViewList = new ArrayList<>();
    List<String> mPicUrlList = new ArrayList<>();

    private String mFlag;
    private AutoScrollViewPager mVpAuto;

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

    }

    @Override
    protected void clickView(View v) {

    }

    public void setFlag(String flag) {

        mFlag = flag;
    }

    @Override
    public void showAds(LunbotuBean response) {

        for (int i = 0; i < response.data.size(); i++) {
            mPicUrlList.add(response.data.get(i).pic_url);

           /* View view = View.inflate(getActivity(), R.layout.item_auto_viewpager, null);
            ImageView ivAutoItem = (ImageView) view.findViewById(R.id.iv_auto_item);
            ImageLoadUtil.LoadImage(getActivity(), mPicUrlList.get(i), ivAutoItem);

            mViewList.add(view);*/

        }

//        mVpAuto.setAdapter(new HomeLunbotuAdapter(mViewList));
        mVpAuto.setPhotoData(mPicUrlList);
        mVpAuto.setBorderAnimation(false);
    }
}
