package com.moelover.moescaner.database;

import android.provider.BaseColumns;

/**
 * Created by 11002311 on 2017/1/13.
 */

public class DownloadContract {

    private DownloadContract(){}

    public  static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "downloads";
        public static final String DOWNLOAD_ID = "download_id";
        public static final String COMPANY = "company";
        public static final String FILE_NAME = "fileName";
        public static final String FILE_URL = "fileURL";
    }
}
