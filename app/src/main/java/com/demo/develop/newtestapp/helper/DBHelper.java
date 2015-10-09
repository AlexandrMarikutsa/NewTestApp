package com.demo.develop.newtestapp.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demo.develop.newtestapp.BuildConfig;

public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "--- onCreate database ---");
        }
        db.execSQL("create table testTable ("
                + "id integer primary key autoincrement,"
                + "rating integer,"
                + "text text,"
                + "timeStamp long" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
