package com.ghx.app.lulu.http.responses;


import com.ghx.app.lulu.http.ApiConstants;

/**
 * Created by guo_hx on 17/1/13.
 */
public class EmptyResponse extends BaseResponse {
    @Override
    public Object getData() {
        return null;
    }

    @Override
    public boolean isSuccessful() {
        return getCode() == ApiConstants.SUCCESS ;
    }
}
