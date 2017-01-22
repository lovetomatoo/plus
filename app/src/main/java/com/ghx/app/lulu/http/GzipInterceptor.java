package com.ghx.app.lulu.http;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by guo_hx on 17/1/16.
 */
// 这个其实是解密模块
public class GzipInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if ("gzip".equals(response.header("Content-Encoding"))) {
            ResponseBody body = response.body();
            ResponseBody outBody = ResponseBody.create(body.contentType(), ungzip(body.byteStream()));

            return response.newBuilder()
                    .headers(response.headers())
                    .removeHeader("Content-Encoding")
                    .body(outBody)
                    .build();
        } else {
            return response;
        }
    }

    public static byte[] ungzip(InputStream in) throws IOException {
        GZIPInputStream pIn = new GZIPInputStream(in);
        byte[] ret = toByteArray(pIn);
        pIn.close();
        return ret;
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output, new byte[1024 * 4]);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output, byte[] bytes)
            throws IOException {
        return copyLarge(input, output, new byte[1024 * 4]);
    }
}
