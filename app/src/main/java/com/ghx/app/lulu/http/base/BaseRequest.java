package com.ghx.app.lulu.http.base;

/**
 * Created by guo_hx on 2016/10/18.15:17
 */

public class BaseRequest<T> {

    private int error;

    private T data;

    public int getError() {
        return error;
    }

    public T getData() {
        return data;
    }

}
