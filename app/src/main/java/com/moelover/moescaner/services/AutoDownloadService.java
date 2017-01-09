package com.moelover.moescaner.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.moelover.moescaner.ApplicationController;
import com.moelover.moescaner.download.ImageDownloadManager;
import com.moelover.moescaner.model.ImageModelYande;
import com.moelover.moescaner.model.ImageViewArray;
import com.moelover.moescaner.net.GsonRequest;

import java.util.ArrayList;

public class AutoDownloadService extends Service {

    ImageViewArray imageViews;
    int position = 0;
    int pageNumber = 0;
    private String strUri = "https://yande.re/post.json?";
    private String strPage = "page=";
    PowerManager.WakeLock wakeLock;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        imageViews = ApplicationController.getInstance().getImageViewArray();
        PowerManager pm  = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, AutoDownloadService.class.getName());
        wakeLock.acquire();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tianlele","onstartservice");
        if(position == imageViews.getImages().size()) {
            //加载更多图片
            loadmorePicture();
        } else {
            ImageDownloadManager.getInstance().download(imageViews.getImages().get(position).getFile_url());
            position++;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void loadmorePicture() {
        pageNumber++;
        GsonRequest<ArrayList<ImageModelYande>> request = new GsonRequest<>(strUri + strPage + pageNumber, new TypeToken<ArrayList<ImageModelYande>>() {}.getType(),
                new Response.Listener<ArrayList<ImageModelYande>>() {
                    @Override
                    public void onResponse(ArrayList<ImageModelYande> response) {
                        imageViews.addImages(response);
                        //开始进行下载
                        ImageDownloadManager.getInstance().download(imageViews.getImages().get(position).getFile_url());
                        position++;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pageNumber--;
                Toast.makeText(AutoDownloadService.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
        ApplicationController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
    }
}
