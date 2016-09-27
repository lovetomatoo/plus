package com.ghx.app.lulu.view;

import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.model.HomeViewPagerBean;

import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:16
 */

public interface IHomeFragmentView extends IBaseView {

    void showList(List<HomeViewPagerBean> mList);

}
