package com.ghx.app.lulu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghx.app.R;
import com.ghx.app.lulu.model.HomeItemRvItemModel;
import com.ghx.app.lulu.model.LunbotuBean;
import com.ghx.app.lulu.utils.LogUtil;
import com.ghx.app.lulu.weiget.autoscroll_viewpager.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2016/10/9.19:44
 */

public class HomeItemRecylerViewAdapter extends RecyclerView.Adapter<HomeItemRecylerViewAdapter.ViewHolder> {

    private Context mContext;

    private List<HomeItemRvItemModel.DataBean> mList = new ArrayList<>();

    public HomeItemRecylerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public HomeItemRecylerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LogUtil.i_log("viewType == " + viewType + "");

        return new ViewHolder(View.inflate(parent.getContext(), R.layout.item_recyleview_homeitem, null));
    }

    @Override
    public void onBindViewHolder(HomeItemRecylerViewAdapter.ViewHolder holder, int position) {

        // onBindViewHolder ____填充View
        Glide.with(mContext).load(mList.get(position).room_src).into(holder.mIvPic);
        holder.mTvName.setText(mList.get(position).nickname);
        holder.mTvPersonNumber.setText(mList.get(position).online + "");
        holder.mTvTitle.setText(mList.get(position).room_name);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<HomeItemRvItemModel.DataBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAllData(List<HomeItemRvItemModel.DataBean> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        AutoScrollViewPager mVpAuto;
        ImageView mIvPic;
        TextView mTvName;
        TextView mTvPersonNumber;
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            mIvPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvPersonNumber = (TextView) itemView.findViewById(R.id.tv_person_number);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            mVpAuto = (AutoScrollViewPager) itemView.findViewById(R.id.vp_auto);

        }
    }
}
