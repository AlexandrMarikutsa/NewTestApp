package com.demo.develop.newtestapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private int rating = 0;

    private Button graph, btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btnSave,btnRead,btnDelete;

    private EditText enterText;

    private DBHelper dbHelper;

    private List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setOnClickListener();
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.save_button:
                Log.d(LOG_TAG, "--- Insert in testTable: ---");
                cv.put("rating", rating);
                cv.put("text", enterText.getText().toString());
                Calendar calendar = Calendar.getInstance();
                cv.put("timeStamp",calendar.getTimeInMillis());
                enterText.getText().clear();
                long rowID = db.insert("testTable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                rating = 0;
                break;
            case R.id.btnRead:
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
                break;
            case R.id.btnDelete:
                Log.d(LOG_TAG, "--- Clear testTable: ---");
                int clearCount = db.delete("testTable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
        }
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
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


    private void initViews(){
        graph = (Button)findViewById(R.id.graph);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btnSave = (Button) findViewById(R.id.save_button);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        enterText = (EditText) findViewById(R.id.edit_text);
        buttons= Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
    }

    private void setOnClickListener(){
        for(final Button button: buttons)
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rating == 0)
                        rating = Integer.parseInt(button.getText().toString());
                }
            });
        btnSave.setOnClickListener(this);
        enterText.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LineChartActivity.class);
                startActivity(intent);
            }
        });
    }

}
