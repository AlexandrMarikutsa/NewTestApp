package com.demo.develop.newtestapp.helper;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

public class Dao {
    final String LOG_TAG = "myLogs";
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public Dao(DBHelper dbHelper){
        this.dbHelper = dbHelper;
        db = dbHelper.getWritableDatabase();
    }

    public void readAll(){
        Log.d(LOG_TAG, "--- Rows in testTable: ---");
        Cursor c = db.query("testTable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int ratingColIndex = c.getColumnIndex("rating");
            int textColIndex = c.getColumnIndex("text");
            int timeStamp = c.getColumnIndex("timeStamp");

            do {
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", ratingColIndex = " + c.getString(ratingColIndex) +
                                ", textColIndex = " + c.getString(textColIndex) +
                                ",timeStamp = " + c.getString(timeStamp));
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

    }

    public void save(int rating, String text){
        ContentValues cv = new ContentValues();
        db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "--- Insert in testTable: ---");
        cv.put("rating", rating);
        cv.put("text", text);
        Calendar calendar = Calendar.getInstance();
        cv.put("timeStamp", calendar.getTimeInMillis());
        long rowID = db.insert("testTable", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }

    public void deleteAll(){
        Log.d(LOG_TAG, "--- Clear testTable: ---");
        int clearCount = db.delete("testTable", null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);
    }
}