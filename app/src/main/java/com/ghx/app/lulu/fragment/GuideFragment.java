package com.ghx.app.lulu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ghx.app.R;

/**
 * Created by guo_hx on 2016/9/13.10:32
 */
public class GuideFragment extends Fragment {

    private static int[] mArray;
    private static int mPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.adapter_guide, container, false);

        ImageView ivSplash = (ImageView) rootView.findViewById(R.id.iv_splash);
        ivSplash.setImageResource(mArray[mPosition]);
        return rootView;
    }

    public static GuideFragment creatInstance(int[] array, int position) {
        mArray = array;
        mPosition = position;
        GuideFragment splashFragment = new GuideFragment();
        return splashFragment;
    }

}
