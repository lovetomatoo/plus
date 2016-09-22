package com.ghx.app.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghx.app.lulu.utils.ToastUtil;

/**
 * Created by guo_hx on 2016/9/22.16:13
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    private BasePresenter mPresenter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                if(getActivity()!=null) {
                    handleMsg(msg);
                }
        }
    };

    public BaseFragment() {

        try {
            mPresenter = (BasePresenter) getPresenter().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(getLayoutId(), container, false);

        mPresenter.initData(savedInstanceState);
        initAllWidget(rootView);
        viewShow();
        return rootView;
    }

    private void viewShow() {
        mPresenter.viewShow();
    }

    protected void handleMsg(Message msg) {
        mPresenter.handleMsg(msg);
    }


    protected abstract int getLayoutId();

    protected abstract Class getPresenter();

    protected abstract void initAllWidget(View rootView);


    @Override
    public Handler getHandler() {
        return mHandler;
    }



    @Override
    public void toast(String str_msg) {
        ToastUtil.showToast("toast");
    }

    @Override
    public void to(Class<?> cls, Bundle bundles) {
        ToastUtil.showToast("to");
    }
}
