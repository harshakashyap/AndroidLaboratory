package com.example.kashyap.thirdlab;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RV = findViewById(R.id.rv);
        RV.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        ArrayList<UserClass> AL = new ArrayList();

        Cursor cursor = dbHelper.getData();
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++)
        {
            UserClass UC = new UserClass();
            UC.setName(cursor.getString(cursor.getColumnIndex("name")));
            UC.setPhone(cursor.getInt(cursor.getColumnIndex("phone")));
            cursor.moveToNext();
            AL.add(UC);
        }

        Adapter adapter = new Adapter(AL);
        RV.setAdapter(adapter);
    }
}
