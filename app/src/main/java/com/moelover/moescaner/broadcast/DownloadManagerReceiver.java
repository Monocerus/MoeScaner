package com.moelover.moescaner.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.moelover.moescaner.services.AutoDownloadService;

/**
 * Created by 11002311 on 2016/12/23.
 */

public class DownloadManagerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        Intent intentServiece = new Intent(context, AutoDownloadService.class);
        context.startService(intentServiece);
    }

}
