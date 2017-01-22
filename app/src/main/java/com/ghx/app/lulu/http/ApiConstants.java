package com.ghx.app.lulu.http;


/**
 * Created by guo_hx on 17/1/12.
 */
public class ApiConstants {

    public static final int PHP_TRUE = 1;
    public static final int PHP_FALSE = 0;

    public static final int LIVE_PORTRAIT = 0;
    public static final int LIVE_LANDSCAPE = 1;

    public static final int CATEGORY_ID_YANZHIKONG = 29;

    public static final int SYSTEM_ERROR = -1;
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int UNLOGIN = 2001;
    public static final int INVALID_PARAMETER = 2002;
    public static final int INVALID_REQUEST = 2003;
    public static final int INVALID_TOKEN = 2004;
    public static final int LIVE_ERROR_REALNAME = 2032; // 需要实名认证
    public static final int LIVE_ERROR_FORBIDDEN = 12001; // 被禁播

    public static final int NEED_BINDING_ACCENT = 2020;

    public static final String DEVICE_ID_SIGNATURE_SALT = "sy13$#%)s*&a12";
    public static final String DEVICE_ID = "dev";
    public static final String DEVICE_ID_SIGNATURE = "sign";


    public static final class LOGIN_MOBILE {
        public static final int VERIFY_CODE_ERROR = 2006;
        public static final int NEED_REGISTER = 2007;
    }

    public static final class REGISTER {
        public static final String MALE = "1";
        public static final String FEMALE = "0";
        public static final String NONE = "-1";
    }

    public static final class SHARE {
        public static final String SELF = "全民";
        public static final String WEIBO = "微博";
        public static final String WECHAT = "微信";
        public static final String WECHAT_CIRCLE = "朋友圈";
        public static final String QQ = "QQ";
        public static final String QZONE = "QQ空间";
    }

    public static final class H5 {
        public static final String UrlAuth = "http://www.shouyintv.cn/app/verify";
        public static final String MoneyHistoryType = "type";
        public static final String MoneyHistoryPay = "recharge"; // [Url]?[MoneyHistoryType]=[MoneyHistoryPay]
        public static final String MoneyHistoryExchange = "exchange"; // [Url]?[MoneyHistoryType]=[MoneyHistoryExchange]
    }

    public final static String URL_IMG = "http://i.img.shouyintv.cn/";
    public final static String URL_AVATAR = "http://a.img.shouyintv.cn/";
    public final static String URL_COVER = "http://c.img.shouyintv.cn/";
    public final static String URL_ATTACHMENT = "http://attchment.shouyintv.cn/";
    public final static String URL_AVATAR_VERIFY = "http://standard.img.shouyincdn.com/";
    public final static String URL_QRCODE_WECHAT_QUANMIN = "http://attchment.shouyin.tv/qmtvewm.jpg";
}
