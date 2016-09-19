package com.ghx.app.lulu.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by guo_hx on 2016/9/19.16:35
 */
public class AnimaUtils {

    /**
     * 渐变补间动画（Alpha Tween Animation）
     * @param start 0-1，0代表完全不可见，1代表完全可见
     * @param end 0-1，0代表完全不可见，1代表完全可见
     * @param duration 动画持续时间
     * @param view 哪个view执行此动画
     */
    public static void alphaAnim (float start, float end, long duration, View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(start, end);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        view.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    /**
     * 平移补间动画（Translate Tween Animation）
     * @param fromXDelta X轴开始位置
     * @param toXDelta X轴结束为止
     * @param fromYDelta Y轴开始位置
     * @param toYDelta Y轴结束位置
     * @param duration 动画持续时间
     * @param view 哪个view执行此动画
     */
    public static void translateAnim (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long duration, View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setDuration(duration);
        view.setAnimation(translateAnimation);
    }

    /**
     *旋转补间动画（Rotate Tween Animation）
     * @param fromDegress 开始角度
     * @param toDegress 结束角度
     * @param pivotX 中心点X轴坐标
     * @param pivotY 中心点Y轴坐标
     * @param duration 动画持续时间
     * @param view 哪个view执行此动画
     */
    public static void rotateAnimation(float fromDegress, float toDegress, int pivotX, int pivotY, long duration, View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, pivotX, pivotY);
        rotateAnimation.setDuration(duration);
        view.setAnimation(rotateAnimation);
    }


    /**
     * 缩放补间动画（Scale Tween Animation）
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     * @param pivotX
     * @param pivotY
     * @param duration
     * @param view
     */
    public static void scaleAnimation (float fromX, float toX, float fromY, float toY, float pivotX, float pivotY, long duration, View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
        scaleAnimation.setDuration(duration);
        view.setAnimation(scaleAnimation);
    }
}
