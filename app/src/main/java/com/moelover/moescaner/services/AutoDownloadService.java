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
import com.moelover.moescaner.database.DownloadDBOperation;
import com.moelover.moescaner.database.ImageDownloadOpenHelper;
import com.moelover.moescaner.download.ImageDownloadManager;
import com.moelover.moescaner.model.ImageModelYande;
import com.moelover.moescaner.model.ImageViewArray;
import com.moelover.moescaner.net.GsonRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class AutoDownloadService extends Service {

    ImageViewArray imageViewArray;
    int pageNumber = 0;
    boolean loadding = false;
    private String strUri = "https://yande.re/post.json?";
    private String strPage = "page=";
    PowerManager.WakeLock wakeLock;
    ImageDownloadOpenHelper imageDownloadOpenHelper;
    DownloadDBOperation dbOperation;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        imageViewArray = new ImageViewArray();
        dbOperation = new DownloadDBOperation(this);
        PowerManager pm  = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, AutoDownloadService.class.getName());
        wakeLock.acquire();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (imageViewArray.getImages().size() <= 0 || loadding) {
                    if (!loadding) {
                        loadmorePicture();
                    }
                }
                ImageDownloadManager.getInstance().download(imageViewArray.getImages().get(0).getFile_url(),imageViewArray.getImages().get(0).getFileName());
                dbOperation.insert(imageViewArray.getImages().get(0));
                imageViewArray.getImages().remove(0);
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
                        for(int i = 0 ;i<response.size();i++) {
                            if(ImageDownloadManager.getInstance().hasFiles(response.get(i).getFileName())) {  //本地已经有了
                                if(!dbOperation.hasDownload(response.get(i).getId())) { //数据库没有
                                    dbOperation.insert(response.get(i));
                                }
                            } else {  //本地没有
                                if(!dbOperation.hasDownload(response.get(i).getId())) {
                                    imageViewArray.getImages().add(response.get(i));
                                }
                            }
                        }
                        loadding = false;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tianlele","第"+pageNumber+"页加载失败。");
                pageNumber--;
                //Toast.makeText(AutoDownloadService.this, "加载失败", Toast.LENGTH_SHORT).show();
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
