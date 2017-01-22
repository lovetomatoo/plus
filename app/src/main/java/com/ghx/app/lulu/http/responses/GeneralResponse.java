package com.ghx.app.lulu.http.responses;

/**
 * Created by guo_hx on 17/1/16.
 */
public class GeneralResponse<T> extends BaseResponse{
    public T data;

    @Override
    public T getData() {
        return data;
    }
}
