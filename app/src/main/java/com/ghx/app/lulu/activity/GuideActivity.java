package com.ghx.app.lulu.activity;

import android.view.View;

import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.lulu.config.Constants;
import com.ghx.app.lulu.utils.SPUtil;
import com.ghx.app.lulu.view.IGuideView;
import com.ghx.app.lulu.presenter.GuidePresenter;
import com.ghx.app.lulu.weiget.GuideView;

/**
 * Created by guo_hx on 2016/9/12.17:11
 * <p/>
 * 这就能看出把几个控件模块化的好处了。
 * <p/>
 * 这只是一个引导页面，逻辑是很简单的，可能还看不出来好处。
 * 但至少可以看出，现在Activity里没有代码。。
 * 如果没有写一个GuideView的话，这个Activity需要处理ViewPager的几个监听，小圆点的动画，隐藏显示等等。
 * <p/>
 * 我觉得这样写代码，贼爽，比如，产品或者UI让我改一改UI，我只需要去找我的GuideView就可以了，如果让我
 * 改业务逻辑，那么我就在这个Activty和Presenter里面找就好了，这里面没啥UI的处理，所以应该非常清晰。
 */
public class GuideActivity extends BaseActivity implements IGuideView {

    private GuideView mGvGuide;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected Class getPresenter() {
        return GuidePresenter.class;
    }

    @Override
    protected void initView() {
        mGvGuide = (GuideView) findViewById(R.id.gv_guide);

        mGvGuide.setOnButtonClickListener(new GuideView.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                to(MainActivity.class);
                new SPUtil(GuideActivity.this).putBoolean(Constants.SP_KEY.FIRST_INSTALL, false);
                finish();
            }
        });

    }

    @Override
    protected void clickView(View v) {

    }

    //这里，我们可能需要对ViewPager做处理，那么只需要让 GuideView 提供一个方法，把ViewPager提供出来就好啦
    @Override
    public void onBackPressed() {
        if (mGvGuide.getViewPager().getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mGvGuide.getViewPager().setCurrentItem(mGvGuide.getViewPager().getCurrentItem() - 1);
        }
    }


}
