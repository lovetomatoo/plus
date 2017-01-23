package com.ghx.app.lulu.model;

import com.ghx.app.base.BaseBean;

import java.util.List;

/**
 * Created by guo_hx on 2016/10/9.19:52
 */

public class HomeItemRvItemModel extends BaseBean {

    public List<DataBean> data;

    public static class DataBean {

        public String anchor_city;
        public String avatar;
        public String avatar_mid;
        public String avatar_small;
        public String cate_id;
        public String child_id;
        public String fans;
        public String game_name;
        public String game_url;
        public int isVertical;
        public String nickname;
        public int online;
        public String owner_uid;
        public int ranktype;
        public String room_id;
        public String room_name;
        public String room_src;
        public String show_status;
        public String show_time;
        public String specific_catalog;
        public String specific_status;
        public String subject;
        public String url;
        public String vertical_src;
        public String vod_quality;
    }
}
