package com.moelover.moescaner.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.moelover.moescaner.ApplicationController;

import java.io.File;



public class ImageDownloadManager {


    private DownloadManager downloadManager;

    private static ImageDownloadManager imageDownloadManager = new ImageDownloadManager();


    private ImageDownloadManager (){
        downloadManager = (DownloadManager) ApplicationController.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
    }


    public static ImageDownloadManager getInstance() {
        return imageDownloadManager;
    }


    public void download(String url ,String strFileName) {
        //Log.d("tianlele",url);
        //Log.d("tianlele",strFileName);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true); //是否显示在下载管理器中
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "yande.re", strFileName);
        if (!file.exists()) {
            request.setDestinationUri(Uri.fromFile(file));
            downloadManager.enqueue(request);
            Log.d("tianlele", "文件"+strFileName+"正在下载...");
        } else {
            Log.d("tianlele","文件"+strFileName+" 已经存在。");
            //Toast.makeText(ApplicationController.getInstance(), "文件已存在", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean hasFiles(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "yande.re", fileName);
        return file.exists();
    }
}
