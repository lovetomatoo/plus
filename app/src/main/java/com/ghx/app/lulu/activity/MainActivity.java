package com.ghx.app.lulu.activity;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ghx.app.lulu.presenter.MainPresenter;
import com.ghx.app.lulu.test.TestActivity;
import com.ghx.app.lulu.utils.AnimaUtils;
import com.ghx.app.lulu.utils.FastBlurUtil;
import com.ghx.app.lulu.utils.ToastUtil;
import com.ghx.app.lulu.view.IMainView;
import com.ghx.app.base.BaseActivity;
import com.ghx.app.R;

public class MainActivity extends BaseActivity implements IMainView {

    private long exitTime = 0;

    private ImageView mIvFuceng;

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

        mIvFuceng = (ImageView) findViewById(R.id.iv_fuceng_main);
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.love_first);
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blur = FastBlurUtil.toBlur(bitmap, 4);
        mIvFuceng.setImageBitmap(blur);
        mIvFuceng.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.iv_fuceng_main: {
                AnimaUtils.alphaAnim(1.0F, 0, 1000, mIvFuceng);
                mIvFuceng.setVisibility(View.GONE);
                to(TestActivity.class);
            }
            break;
        }
    }



    //两次退出应用
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
