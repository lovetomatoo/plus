package com.ghx.app.lulu.http.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public abstract class RequestMsg {
    //请求参数
    protected HashMap<String, Object> params = new HashMap<String, Object>();

    protected boolean isAddStatisticsParams(){
        if (getUrl()!=null && getUrl().contains(".json")){
            return false;
        }else {
            return true;
        }
    }


    protected RequestMsg()
    {
//        put("v", Utils.version);
//        put("os", Utils.os);
//        put("ver", Utils.ver);
    }

    protected RequestMsg(Map<String,String> map){
        if (map!=null){
            for(Map.Entry<String,String> entry : map.entrySet()){
                params.put(entry.getKey(),entry.getValue());
            }
        }
    }

    abstract public String getUrl();

    public String getRequestUrl(){
        if (isAddStatisticsParams()){
//            return LoggerManager.addParams(getUrl());
            return "";
        }else{
            return getUrl();
        }
    }

//    {
//        if (!TextUtils.isEmpty(HttpConfig.getBaseJsonUrl())) {
//            return HttpConfig.getBaseJsonUrl() + getActionName();
//        } else
//            return null;
//    }

    public HashMap<String, Object> getHashMapParams() {
        return params;
    }


    public Set<Map.Entry<String, Object>> getParamEntry() {

        return params.entrySet();
    }

    /*public String getJsonBody() {

        String query = "";
        query = FastJSONHelper.toJSONStr(params);
        return query;
    }*/

//    abstract public String getActionName();

    public void put(String key, String value) {

        if(value!=null) {
            params.put(key, value);
        }
    }

    public void put(String key, int value) {
        Integer integer = new Integer(value);
        params.put(key, integer);
    }


    public void put(String key, File file) {
        params.put(key, file);
    }

    public void put(String key, double value) {
        Double d = new Double(value);
        params.put(key, d);
    }

    public void put(String key, boolean value) {
        Integer integer;
        if (value)
            integer = new Integer(1);
        else
            integer = new Integer(0);

        params.put(key, integer);
    }

    public void put(String key, List l) {
        if(l!=null) {
            params.put(key, l);
        }
    }

    public void put(String key, Map map) {
        if(map!=null) {
            params.put(key, map);
        }
    }

    //    获取系统时间
    protected String getCurTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}