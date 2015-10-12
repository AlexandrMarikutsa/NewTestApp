package com.demo.develop.newtestapp.helper;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.demo.develop.newtestapp.BuildConfig;
import com.demo.develop.newtestapp.classes.Click;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.demo.develop.newtestapp.constants.DataBase.ID;
import static com.demo.develop.newtestapp.constants.DataBase.RATING;
import static com.demo.develop.newtestapp.constants.DataBase.TABLE_NAME;
import static com.demo.develop.newtestapp.constants.DataBase.TEXT;
import static com.demo.develop.newtestapp.constants.DataBase.TIME_STAMP;

public class Dao {
    final String LOG_TAG = "myLogs";
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public Dao(DBHelper dbHelper){
        this.dbHelper = dbHelper;
        db = dbHelper.getWritableDatabase();
    }

    public List<Click> readAll(){
        List<Click> clicks = new ArrayList<>();
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "--- Rows in testTable: ---");
        }
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex(ID);
            int ratingColIndex = c.getColumnIndex(RATING);
            int textColIndex = c.getColumnIndex(TEXT);
            int timeStamp = c.getColumnIndex(TIME_STAMP);
            do {
                if (BuildConfig.DEBUG) {
                    Log.d(LOG_TAG,
                            "ID = " + c.getInt(idColIndex) +
                                    ", ratingColIndex = " + c.getString(ratingColIndex) +
                                    ", textColIndex = " + c.getString(textColIndex) +
                                    ",timeStamp = " + c.getString(timeStamp));
                }
                clicks.add(new Click(c.getInt(ratingColIndex),c.getLong(timeStamp)));
            } while (c.moveToNext());
        } else
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "0 rows");
        }
        c.close();
        return clicks;
    }

    public void save(int rating, String text){
        ContentValues cv = new ContentValues();
        db = dbHelper.getWritableDatabase();
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "--- Insert in testTable: ---");
        }
        cv.put(RATING, rating);
        cv.put(TEXT, text);
        Calendar calendar = Calendar.getInstance();
        cv.put(TIME_STAMP, calendar.getTimeInMillis());
        long rowID = db.insert(TABLE_NAME, null, cv);
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }
    }

    public void deleteAll(){
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "--- Clear testTable: ---");
        }
        int clearCount = db.delete("testTable", null, null);
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "deleted rows count = " + clearCount);
        }
    }
}
