package com.example.waste_master_two;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table myTable(routeNo INTEGER PRIMARY KEY AUTOINCREMENT, routename TEXT ," +
                "routeNo TEXT,contectNo TEXT,date INTEGER,item TEXT,qty INTEGER,amount INTEGER);";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String routeName,String routeNo,long date,String item,int qty,int amount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("routeName",routeName);
        contentValues.put("routeNo",routeNo);
        contentValues.put("date",date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);
        sqLiteDatabase.insert("myTable",null,contentValues);
    }
}
