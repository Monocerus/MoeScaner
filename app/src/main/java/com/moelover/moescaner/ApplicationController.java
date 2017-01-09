package com.moelover.moescaner;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.moelover.moescaner.model.ImageViewArray;
import com.moelover.moescaner.net.MyImageDownloader;
import com.moelover.moescaner.net.OkHttpStack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by tianle on 2015/12/6.
 */
public class ApplicationController extends Application{
    /**
     * Log or request TAG
     */
    public static final String TAG = "ApplicationController";

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;

    /**
     * A singleton instance of the application class for easy access in other
     * places
     */
    private static ApplicationController sInstance;

    private ImageViewArray imageViewArray;

    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new FadeInBitmapDisplayer(500))
            .showImageOnLoading(R.drawable.background_empty)
            .showImageOnFail(R.drawable.background_error)
            .build();

    private static DisplayImageOptions options2 = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new FadeInBitmapDisplayer(100))
            .showImageOnFail(R.drawable.background_error)
            .build();

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the singleton
        sInstance = this;

        //
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .imageDownloader(new MyImageDownloader(this))
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
        imageViewArray = new ImageViewArray();
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {

            synchronized (ApplicationController.class) {
                if (mRequestQueue == null) {
                    mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
                }
            }
        }
        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified then
     * it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



    public DisplayImageOptions getOptions() {
        return options;
    }

    public DisplayImageOptions getOptions2() {
        return options2;
    }

    public ImageViewArray getImageViewArray() {
        return imageViewArray;
    }

    public void setImageViewArray(ImageViewArray imageViewArray) {
        this.imageViewArray = imageViewArray;
    }
}
