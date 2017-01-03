package com.moelover.moescaner.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.moelover.moescaner.ApplicationController;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



public class ImageDownloadManager {


    private DownloadManager downloadManager;

    private static ImageDownloadManager imageDownloadManager = new ImageDownloadManager();


    private ImageDownloadManager (){
        downloadManager = (DownloadManager) ApplicationController.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
    }


    public static ImageDownloadManager getInstance() {
        return imageDownloadManager;
    }


    public void download(String url) {
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "MoeLover", uri.getLastPathSegment());
        if (!file.exists()) {
            request.setDestinationUri(Uri.fromFile(file));
            downloadManager.enqueue(request);
        } else {
            Toast.makeText(ApplicationController.getInstance(), "文件已存在", Toast.LENGTH_SHORT).show();
        }

    }

    public void download(String[] urls) {
        for (String url : urls) {

        }
    }
}
