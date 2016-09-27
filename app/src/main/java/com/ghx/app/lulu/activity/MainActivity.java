package com.ghx.app.lulu.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.ghx.app.lulu.adapter.MainViewPagerAdapter;
import com.ghx.app.lulu.fragment.home.FourthFragment;
import com.ghx.app.lulu.fragment.home.HomeFragment;
import com.ghx.app.lulu.fragment.home.SecondFragment;
import com.ghx.app.lulu.fragment.home.ThirdFragment;
import com.ghx.app.lulu.presenter.MainPresenter;
import com.ghx.app.lulu.utils.AnimaUtils;
import com.ghx.app.lulu.utils.FastBlurUtil;
import com.ghx.app.lulu.utils.ToastUtil;
import com.ghx.app.lulu.view.IMainView;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.R;
import com.ghx.app.lulu.weiget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private long exitTime = 0;

    private ImageView mIvFuceng;
    private NoScrollViewPager mViewPager;
    private List<Fragment> mList = new ArrayList<>();
    private RadioGroup mRadioGroup;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected Class getPresenter() {

        return MainPresenter.class;
    }

    @Override
    protected void initView() {

        initFragment();


        mIvFuceng = (ImageView) findViewById(R.id.iv_fuceng_main);
        mViewPager = (NoScrollViewPager) findViewById(R.id.viewpager_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_main);

        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.love_first);
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blur = FastBlurUtil.toBlur(bitmap, 4);
        mIvFuceng.setImageBitmap(blur);
        mIvFuceng.setOnClickListener(this);

        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), mList));

        mRadioGroup.check(1);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(checkedId - 1, false);
            }
        });

        mViewPager.setOffscreenPageLimit(100);
    }

    private void initFragment() {

        HomeFragment homeFragment = new HomeFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        FourthFragment fourthFragment = new FourthFragment();

        mList.add(homeFragment);
        mList.add(secondFragment);
        mList.add(thirdFragment);
        mList.add(fourthFragment);
    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.iv_fuceng_main: {
                AnimaUtils.alphaAnim(1.0F, 0, 1000, mIvFuceng);
                mIvFuceng.setVisibility(View.GONE);
                mIvFuceng.setClickable(false);
            }
            break;
        }
    }



    //两次Back退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("guohongxin", "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast("再按一下返回键退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
