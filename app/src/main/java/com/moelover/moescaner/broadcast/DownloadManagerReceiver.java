package com.moelover.moescaner.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 11002311 on 2016/12/23.
 */

public class DownloadManagerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }


//    private void queryDownloadStatus() {
//        DownloadManager.Query query = new DownloadManager.Query();
//        query.setFilterById(prefs.getLong(DL_ID, 0));
//        Cursor c = downloadManager.query(query);
//        if(c.moveToFirst()) {
//            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            switch(status) {
//            case DownloadManager.STATUS_PAUSED:
//                Log.v("down", "STATUS_PAUSED");
//            case DownloadManager.STATUS_PENDING:
//                Log.v("down", "STATUS_PENDING");
//            case DownloadManager.STATUS_RUNNING:
//                //正在下载，不做任何事情
//                Log.v("down", "STATUS_RUNNING");
//                break;
//            case DownloadManager.STATUS_SUCCESSFUL:
//                //完成
//                Log.v("down", "下载完成");
//                break;
//            case DownloadManager.STATUS_FAILED:
//                //清除已下载的内容，重新下载
//                Log.v("down", "STATUS_FAILED");
//                downloadManager.remove(prefs.getLong(DL_ID, 0));
//                prefs.edit().clear().commit();
//                break;
//            }
//        }
//    }
}
