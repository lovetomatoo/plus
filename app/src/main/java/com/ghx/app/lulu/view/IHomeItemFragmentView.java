package com.ghx.app.lulu.view;

import com.ghx.app.base.IBaseView;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;

/**
 * Created by guo_hx on 2016/9/26.17:13
 */

public interface IHomeItemFragmentView extends IBaseView {

    void showAds(LunbotuBean body);

    void showItem(HomeItemRvItemModel body);
}
