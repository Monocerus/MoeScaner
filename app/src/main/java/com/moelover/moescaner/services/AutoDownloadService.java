package com.moelover.moescaner.services;

import android.app.DownloadManager;
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
import com.moelover.moescaner.broadcast.DownloadManagerReceiver;
import com.moelover.moescaner.download.ImageDownloadManager;
import com.moelover.moescaner.model.ImageModelYande;
import com.moelover.moescaner.model.ImageViewArray;
import com.moelover.moescaner.net.GsonRequest;

import java.util.ArrayList;
import java.util.LinkedList;

public class AutoDownloadService extends Service {

    LinkedList<String> downloadurl;
    int pageNumber = 0;
    boolean loadding = false;
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
        downloadurl = new LinkedList<>();
        PowerManager pm  = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, AutoDownloadService.class.getName());
        wakeLock.acquire();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(downloadurl.size() <= 0) {
                    while(downloadurl.size() <= 0) {
                        if(!loadding) {
                            loadmorePicture();
                        }
                    }
                }
                //Log.d("tianlele","当前下载图片"+downloadurl.getFirst());
                ImageDownloadManager.getInstance().download(downloadurl.getFirst());
                downloadurl.removeFirst();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void loadmorePicture() {
        pageNumber++;
        loadding = true;
        Log.d("tianlele","当前加载第"+pageNumber+"页");
        GsonRequest<ArrayList<ImageModelYande>> request = new GsonRequest<>(strUri + strPage + pageNumber, new TypeToken<ArrayList<ImageModelYande>>() {}.getType(),
                new Response.Listener<ArrayList<ImageModelYande>>() {
                    @Override
                    public void onResponse(ArrayList<ImageModelYande> response) {
                        //添加为下载的图片
                        for(ImageModelYande imageModelYande : response) {
                            if(!ImageDownloadManager.getInstance().hasFiles(imageModelYande.getFile_url())) {
                                downloadurl.add(imageModelYande.getFile_url());
                            }
                        }
                        loadding = false;
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
