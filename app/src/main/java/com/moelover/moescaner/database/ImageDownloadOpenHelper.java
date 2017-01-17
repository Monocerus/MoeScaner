package com.moelover.moescaner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.moelover.moescaner.download.ImageDownloadManager;

/**
 * Created by 11002311 on 2017/1/11.
 */

public class ImageDownloadOpenHelper extends SQLiteOpenHelper {

    /** Schema version. */
    public static final int DATABASE_VERSION = 1;
    /** Filename for SQLite file. */
    public static final String DATABASE_NAME = "moelover.db";

    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String COMMA_SEP = ",";
    /** SQL statement to create "entry" table. */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DownloadContract.Entry.TABLE_NAME + " (" +
                    DownloadContract.Entry._ID + " INTEGER  PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    DownloadContract.Entry.DOWNLOAD_ID + TYPE_INTEGER + COMMA_SEP +
                    DownloadContract.Entry.COMPANY    + TYPE_TEXT + COMMA_SEP +
                    DownloadContract.Entry.FILE_NAME + TYPE_TEXT + COMMA_SEP +
                    DownloadContract.Entry.FILE_URL + TYPE_TEXT + ")";

    public ImageDownloadOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("tinalele","onCreate database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
