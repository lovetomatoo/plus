package com.ghx.app.lulu.http.base;

/**
 * Created by guo_hx on 2016/10/20.13:43
 */

public class BaseResponse<T> {

    public int code;
    public String msg;
    public T data;

    public boolean isOk() {
        return code == 0;
    }
}
