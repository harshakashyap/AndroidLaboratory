package com.example.kashyap.thirdlab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ThirdLab", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table Details(name text, phone integer) ");
        sqLiteDatabase.execSQL("insert into Details values ('Harsha',12345) ");
        sqLiteDatabase.execSQL("insert into Details values ('Kashyap',45454) ");
        sqLiteDatabase.execSQL("insert into Details values ('Buddi',75757) ");
        sqLiteDatabase.execSQL("insert into Details values ('Abba',45232) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Details");
        onCreate(sqLiteDatabase);
    }

    public void insertData(String name, int phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into Details values('"+name+"','"+phone+"')");
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Details",null);
        return cursor;
    }
}
