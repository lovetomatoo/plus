package com.ghx.app.lulu.http.responses;


import com.ghx.app.lulu.http.ApiConstants;
import com.ghx.app.lulu.http.ServerResponseException;

/**
 * Created by guo_hx on 17/1/11.
 */
public abstract class BaseResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract Object getData();

    public boolean isSuccessful() {
        return code == ApiConstants.SUCCESS && getData() != null;
    }

    public void assertSuccessful() {
        if (!isSuccessful()) {
            if (getCode() == ApiConstants.UNLOGIN) {
//                if (UserInfoManager.hasLogin()) {
//                    UserInfoManager.onLogout();
//                }
            }
            throw new ServerResponseException(message);
        }
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message +
                ", data='" + getData() +
                '}';
    }
}
