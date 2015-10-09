package com.demo.develop.newtestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.demo.develop.newtestapp.helper.DBHelper;
import com.demo.develop.newtestapp.helper.Dao;

public class MainActivity extends Activity implements View.OnClickListener {

    private int rating = 0;

    private Button graph, btnSave,btnRead,btnDelete;

    private ToggleButton currentButton;
    private int[] buttonIds = new int[]{R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5
                                        , R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10};

    private EditText enterText;

    private DBHelper dbHelper;

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
        Dao dao = new Dao(dbHelper);
        switch (v.getId()) {
            case R.id.save_button:
                String text = enterText.getText().toString();
                enterText.getText().clear();
                dao.save(rating,text);
                rating = 0;
                break;
            case R.id.btnRead:
                dao.readAll();
                break;
            case R.id.btnDelete:
                dao.deleteAll();
                break;
        }
        dbHelper.close();
    }

    private void initViews(){
        graph = (Button)findViewById(R.id.graph);
        for (int id : buttonIds) {
            ToggleButton button = (ToggleButton) findViewById(id);
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (currentButton != null) {
                        //currentButton.;
                    }

                    currentButton = (ToggleButton) buttonView;
                }
            });
        }
        btnSave = (Button) findViewById(R.id.save_button);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        enterText = (EditText) findViewById(R.id.edit_text);
    }

    private void setOnClickListener(){
        btnSave.setOnClickListener(this);
        enterText.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Graph.class);
                startActivity(intent);
            }
        });


    }

}
