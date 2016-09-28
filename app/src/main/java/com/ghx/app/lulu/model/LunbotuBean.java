package com.ghx.app.lulu.model;

import com.ghx.app.base.BaseBean;

import java.util.List;

/**
 * Created by guo_hx on 2016/9/28.15:08
 */

public class LunbotuBean extends BaseBean {

    public List<LunbotuItemBean> data;

    public class LunbotuItemBean {

        public String id;
        public String pic_url;
        public Room room;
        public String title;
        public String tv_pic_url;

        public class Room {

            public String cate_id;
            public List<CdnsWithName> cdnsWithName;
            public class CdnsWithName {

                public String cdn;
                public String name;

                @Override
                public String toString() {
                    return "CdnsWithName{" +
                            "cdn='" + cdn + '\'' +
                            ", name='" + name + '\'' +
                            '}';
                }
            }
            public String column_id;
            public String credit_illegal;
            public String cur_credit;
            public String fans;
            public String game_icon_url;
            public String game_name;
            public String game_url;
            public String isVertical;
            public String is_pass_player;
            public String is_white_list;
            public String low_credit;
            public String nickname;

            public String online;
            public String owner_avatar;
            public String owner_uid;
            public String owner_weight;
            public String room_id;
            public String room_name;
            public String room_src;
            public String show_details;
            public String show_status;
            public String show_time;
            public String specific_catalog;
            public String specific_status;
            public String url;
            public String vertical_src;
            public String vod_quality;

            @Override
            public String toString() {
                return "Room{" +
                        "cate_id='" + cate_id + '\'' +
                        ", cdnsWithName=" + cdnsWithName +
                        ", column_id='" + column_id + '\'' +
                        ", credit_illegal='" + credit_illegal + '\'' +
                        ", cur_credit='" + cur_credit + '\'' +
                        ", fans='" + fans + '\'' +
                        ", game_icon_url='" + game_icon_url + '\'' +
                        ", game_name='" + game_name + '\'' +
                        ", game_url='" + game_url + '\'' +
                        ", isVertical='" + isVertical + '\'' +
                        ", is_pass_player='" + is_pass_player + '\'' +
                        ", is_white_list='" + is_white_list + '\'' +
                        ", low_credit='" + low_credit + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", online='" + online + '\'' +
                        ", owner_avatar='" + owner_avatar + '\'' +
                        ", owner_uid='" + owner_uid + '\'' +
                        ", owner_weight='" + owner_weight + '\'' +
                        ", room_id='" + room_id + '\'' +
                        ", room_name='" + room_name + '\'' +
                        ", room_src='" + room_src + '\'' +
                        ", show_details='" + show_details + '\'' +
                        ", show_status='" + show_status + '\'' +
                        ", show_time='" + show_time + '\'' +
                        ", specific_catalog='" + specific_catalog + '\'' +
                        ", specific_status='" + specific_status + '\'' +
                        ", url='" + url + '\'' +
                        ", vertical_src='" + vertical_src + '\'' +
                        ", vod_quality='" + vod_quality + '\'' +
                        '}';
            }

        }

        @Override
        public String toString() {
            return "LunbotuItemBean{" +
                    "id='" + id + '\'' +
                    ", pic_url='" + pic_url + '\'' +
                    ", room=" + room +
                    ", title='" + title + '\'' +
                    ", tv_pic_url='" + tv_pic_url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LunbotuBean{" +
                "data=" + data +
                '}';
    }
}
