package com.ghx.app.lulu.presenter;

import android.os.Bundle;
import android.os.Message;

import com.ghx.app.base.BasePresenter;
import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.model.HomeViewPagerBean;
import com.ghx.app.lulu.utils.ToastUtil;
import com.ghx.app.lulu.view.IHomeFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/22.17:01
 */

public class HomeFragmentPresenter extends BasePresenter<IHomeFragmentView> {

    private List<HomeViewPagerBean> mList = new ArrayList<>();

    @Override
    public void handleMsg(Message msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void viewShow() {
        super.viewShow();

        mList.clear();

        //模拟数据
        for (int i = 0; i < 5; i++) {
            HomeViewPagerBean homeViewPagerBean = new HomeViewPagerBean();
            homeViewPagerBean.id = "id = " + i;
            homeViewPagerBean.title = "英雄联盟";
            homeViewPagerBean.pic_url = "pic_url = " + i;
            mList.add(homeViewPagerBean);
        }

        //数据有了以后，交给Activity展示
        iView.showList(mList);
        ToastUtil.showToast("viewShow" + mList.size());
    }
}
