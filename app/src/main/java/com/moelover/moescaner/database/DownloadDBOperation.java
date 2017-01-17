package com.moelover.moescaner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.moelover.moescaner.model.ImageModelYande;

/**
 * Created by 11002311 on 2017/1/13.
 */

public class DownloadDBOperation {
    ImageDownloadOpenHelper imageDownloadOpenHelper;

    public  DownloadDBOperation(Context context) {
        imageDownloadOpenHelper = new ImageDownloadOpenHelper(context);
    }

    public boolean hasDownload(long downloadID) {
        SQLiteDatabase db = imageDownloadOpenHelper.getReadableDatabase();
        String[] projection = {DownloadContract.Entry._ID};
        String selection = DownloadContract.Entry.DOWNLOAD_ID +"=?";
        String[] selectionArg = {String.valueOf(downloadID)};
        Cursor cursor = db.query(DownloadContract.Entry.TABLE_NAME,projection,selection,selectionArg,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public void insert(ImageModelYande mode) {
        SQLiteDatabase db = imageDownloadOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DownloadContract.Entry.DOWNLOAD_ID,mode.getId());
        contentValues.put(DownloadContract.Entry.COMPANY,"yande.re");
        contentValues.put(DownloadContract.Entry.FILE_NAME,mode.getFileName());
        contentValues.put(DownloadContract.Entry.FILE_URL,mode.getFile_url());
        db.insert(DownloadContract.Entry.TABLE_NAME,null,contentValues);
    }

}
