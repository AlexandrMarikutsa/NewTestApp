package com.demo.develop.newtestapp.helper;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

public class Dao {
    final String LOG_TAG = "myLogs";
    private ContentValues cv;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public Dao(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void save(int rating, String text){
        cv = new ContentValues();
        db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "--- Insert in testTable: ---");
        cv.put("rating", rating);
        cv.put("text", text);
        Calendar calendar = Calendar.getInstance();
        cv.put("timeStamp", calendar.getTimeInMillis());
        long rowID = db.insert("testTable", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }
}
