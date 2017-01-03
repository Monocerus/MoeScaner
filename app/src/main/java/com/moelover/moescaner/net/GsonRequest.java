package com.moelover.moescaner.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Type type;
    private Gson mGson;

    public GsonRequest(String url, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, type, listener, errorListener);
    }

    public GsonRequest(int method, String url, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.type = type;
        this.mGson = new Gson();
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        T t = mGson.fromJson(parsed, this.type);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> headMap = new HashMap<String,String>();
        headMap.put("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
        return headMap;
    }
}
