package com.moelover.moescaner.net;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * 该类重写了ImageDownloa的请求头，使其不会被拒绝访问
 */
public class MyImageDownloader extends BaseImageDownloader {

    public MyImageDownloader(Context context) {
        super(context);
    }


    @Override
    protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
        HttpURLConnection conn = super.createConnection(url, extra);
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        return conn;
    }
}

