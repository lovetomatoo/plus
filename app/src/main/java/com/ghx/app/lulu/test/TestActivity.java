package com.ghx.app.lulu.test;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.ghx.app.R;
import com.ghx.app.base.BaseActivity;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by guo_hx on 2016/9/21.11:53
 */

public class TestActivity extends BaseActivity implements ITestView {

    ImageView iv;
    private Button mBtnTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected Class getPresenter() {
        return TestPresenter.class;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this,"1751c683fddb7");

        mBtnTest = (Button) findViewById(R.id.btn_test);

        mBtnTest.setOnClickListener(this);

    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.btn_test: {
                showShare();
            }

            break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
