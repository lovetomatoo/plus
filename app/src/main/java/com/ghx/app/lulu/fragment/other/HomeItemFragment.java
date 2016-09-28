package com.ghx.app.lulu.fragment.other;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghx.app.R;
import com.ghx.app.base.BaseFragment;
import com.ghx.app.lulu.presenter.HomeItemFragmentPresenter;
import com.ghx.app.lulu.view.IHomeItemFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/9/26.17:10
 */

public class HomeItemFragment extends BaseFragment<HomeItemFragmentPresenter> implements IHomeItemFragmentView {

    List<View> mViewList = new ArrayList<>();

    private String mFlag;
    private ViewPager mVpAuto;

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

        mVpAuto = (ViewPager) rootView.findViewById(R.id.vp_auto);

        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(getActivity());
            View view = View.inflate(getActivity(), R.layout.item_auto_viewpager, null);

            switch (mFlag) {
                case "id = " + 0:
                    textView.setText(i + "00000哈哈，我是都比，你能咋地");
                    break;
                case "id = " + 1:
                    textView.setText(i + "11111哈哈，我是都比，你能咋地");
                    break;
                case "id = " + 2:
                    textView.setText(i + "22222哈哈，我是都比，你能咋地");
                    break;
                case "id = " + 3:
                    textView.setText(i + "33333哈哈，我是都比，你能咋地");
                    break;
                case "id = " + 4:
                    textView.setText(i + "44444哈哈，我是都比，你能咋地");
                    break;
            }
            mViewList.add(view);
        }

        mVpAuto.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                ((ViewPager) container).addView(mViewList.get(position), 0);

                return mViewList.get(position);
            }

            @Override
            public void destroyItem(View view, int position, Object object) {
                ((ViewPager) view).removeView(mViewList.get(position));
            }


        });


        mPresenter.getServerData(mFlag);

    }

    @Override
    protected void clickView(View v) {

    }

    public void setFlag(String flag) {

        mFlag = flag;
    }
}
