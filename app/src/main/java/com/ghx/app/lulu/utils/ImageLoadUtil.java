package com.ghx.app.lulu.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by guo_hx on 2016/9/28.14:26
 */

public class ImageLoadUtil {

    public static void LoadImage(Context context, String url, ImageView view) {

        Glide.with(context).load(url).into(view);
    }
}
