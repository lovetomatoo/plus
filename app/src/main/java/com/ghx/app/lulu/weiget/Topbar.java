package com.ghx.app.lulu.weiget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghx.app.R;
import com.ghx.app.lulu.utils.ToastUtil;

/**
 * Created by guo_hx on 2016/9/23.11:34
 */

public class Topbar extends RelativeLayout implements View.OnClickListener {

    private String str_title="";
    private Drawable draw_right=null;


    Context context;

    //顶部一些控件 begin
    TextView txt_btn_back;
    TextView txt_title;
    TextView txt_btn_right;
    ImageView img_right;
    private On2ClickListener twoClickListener;
    private long exitTime;
    //顶部一些控件 end

    public Topbar(Context context) {
        super(context);
        this.context = context;
        initWidgets();
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttr(context,attrs);
        initWidgets();
    }

    public Topbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initAttr(context,attrs);
        initWidgets();
    }

    private void initWidgets() {
        LayoutInflater.from(context).inflate(R.layout.layout_header, this);
        txt_btn_back =(TextView) findViewById(R.id.txt_btn_back);
        txt_title = (TextView) findViewById(R.id.txt_title);
        img_right = (ImageView) findViewById(R.id.img_right);
        txt_btn_right = (TextView) findViewById(R.id.txt_btn_right);

        txt_btn_back.setOnClickListener(this);
        setOnClickListener(this);

        if(!TextUtils.isEmpty(str_title)) {
            txt_title.setText("" + str_title);
        }

        if(draw_right!=null) {
            img_right.setImageDrawable(draw_right);
        }
    }

    private void initAttr(Context context, AttributeSet attrs)
    {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        str_title = array.getString(R.styleable.Topbar_top_title);
        draw_right=array.getDrawable(R.styleable.Topbar_right_drawable);
        array.recycle();
    }

    /**
     * 隐藏返回按钮和文字
     */
    public void hideBackBtn()
    {
        txt_btn_back.setVisibility(GONE);
    }


    public void setTitle(String title) {
        txt_title.setText("" + title);
    }

    //设置右边图标
    public void setRightImg(int resId) {
        img_right.setImageDrawable(context.getResources().getDrawable(resId));
        img_right.setVisibility(View.VISIBLE);
    }
    //设置左边边图标
    public void setLeftImg(int resId)
    {
        Drawable drawable= getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        txt_btn_back.setCompoundDrawables(drawable,null,null,null);
    }

    //设置右边文字
    public void setRightText(String str_right) {
        txt_btn_right.setText(str_right);
    }
    public void setRightTextColor(int color){
        txt_btn_right.setTextColor(getResources().getColor(color));
    }

    //设置右边按钮的点击事件
    public void setRightClickListener(OnClickListener listener) {
        img_right.setOnClickListener(listener);
        txt_btn_right.setOnClickListener(listener);
    }

    public void setOnTopbar2ClickListener(On2ClickListener listener) {
        twoClickListener = listener;
    }


    public int getRightTxtId()
    {
        return R.id.txt_btn_right;
    }
    public int getRightImgId()
    {
        return R.id.img_right;
    }

    public TextView getRightTextView()
    {
        return txt_btn_right;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_back: {
                if (context instanceof Activity) {

                    if (!((Activity) context).isFinishing())
                        ((Activity) context).finish();
                }
            }
            break;
            default: {
                if ((System.currentTimeMillis() - exitTime) > 1000) {
                    exitTime = System.currentTimeMillis();
                } else {
                    twoClickListener.setOn2ClickListener();
                }
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            findViewById(R.id.txt_btn_back).performClick();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface On2ClickListener {
        void setOn2ClickListener();
    }

}
