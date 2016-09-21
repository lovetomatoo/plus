package com.ghx.app.lulu.weiget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.ghx.app.R;
import com.ghx.app.lulu.utils.ScreenUtils;

/**
 * Created by guo_hx on 2016/9/21.14:59
 * <p>
 * 这个自定义控件，很有意思的。
 * 继承自ImageView
 */

public class InterstingImageView extends ImageView {

    public InterstingImageView(Context context) {
        this(context, null);
    }

    public InterstingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InterstingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //1. 获取模特位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.love_nine);

        //获取到屏幕的宽和高
        final int width = ScreenUtils.getScreenWidth(getContext());
        final int height = ScreenUtils.getScreenHeight(getContext());

        //2. 创建一张空白画纸
        final Bitmap bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //3. 创建一个画板，并把白纸粘上去
        Canvas canvas = new Canvas(bitmap2);
        //4. 将模特画到纸上
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, null);
        //5. 将白纸（已经不是白纸了）设置到ImageView上
        setImageBitmap(bitmap2);
        //6. 给ImageView设置滑动监听事件
        setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    //获取到当前滑到的坐标
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    int startX = x - 10 > 0 ? x - 10 : 0;
                    int endX = x + 10 < width ? x + 10 : width;

                    int startY = y - 10 > 0 ? y - 10 : 0;
                    int endY = y + 10 < height ? y + 10 : height;

                    for (int i = startX; i < endX; i++) {
                        for (int j = startY; j < endY; j++) {
                            //将bitmap2中指定的像素点设置为透明的
                            bitmap2.setPixel(i, j, Color.TRANSPARENT);
                        }
                    }
                    //由于bitmap2的属性修改了，因此需要重新设置到ImageView上
                    setImageBitmap(bitmap2);
                }
                return true;
            }
        });

    }
}
