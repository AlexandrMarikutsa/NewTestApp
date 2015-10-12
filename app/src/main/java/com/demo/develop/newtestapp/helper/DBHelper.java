package com.demo.develop.newtestapp.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demo.develop.newtestapp.BuildConfig;

import static com.demo.develop.newtestapp.constants.DataBase.DATABASE_NAME;
import static com.demo.develop.newtestapp.constants.DataBase.ID;
import static com.demo.develop.newtestapp.constants.DataBase.RATING;
import static com.demo.develop.newtestapp.constants.DataBase.TABLE_NAME;
import static com.demo.develop.newtestapp.constants.DataBase.TEXT;
import static com.demo.develop.newtestapp.constants.DataBase.TIME_STAMP;

public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "--- onCreate database ---");
        }
        db.execSQL("create table "+ TABLE_NAME+" ("
                + ID +" integer primary key autoincrement,"
                + RATING +" integer,"
                + TEXT + " text,"
                + TIME_STAMP + " long" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
