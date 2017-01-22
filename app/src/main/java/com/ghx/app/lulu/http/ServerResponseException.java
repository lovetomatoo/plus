package com.ghx.app.lulu.http;

/**
 * Created by guo_hx on 17/1/13.
 */
public class ServerResponseException extends RuntimeException {
    public ServerResponseException() {
        super();
    }

    public ServerResponseException(String detailMessage) {
        super(detailMessage);
    }

    public ServerResponseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServerResponseException(Throwable throwable) {
        super(throwable);
    }
}
