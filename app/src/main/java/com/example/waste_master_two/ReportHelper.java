package com.example.waste_master_two;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ReportHelper extends SQLiteOpenHelper {
    public ReportHelper(@Nullable Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table myTable(invoiceNo INTEGER PRIMARY KEY AUTOINCREMENT,driverName TEXT,contactNo TEXT,date INTEGER,item TEXT,qty INTEGER,amount INTEGER);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(String driverName, String contactNo, long date, String item, int qty, int amount){

        //connect to the database in our system
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("driverName",driverName);
        contentValues.put("contactNo",contactNo);
        contentValues.put("date",date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);
        sqLiteDatabase.insert("myTable",null,contentValues);
    }
}
