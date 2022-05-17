package com.example.waste_master_two;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SplittableRandom;

public class RouteReportGenarate extends AppCompatActivity {

        Button saveAndPrintButton,printButton;
        EditText editTextName,editTextPhone,editTextQty;
        Spinner spinner;
        String[] itemList;
        int[] itemPrice;
        ArrayAdapter<String> adapter;
        ReportHelper reportHelper;
        SQLiteDatabase sqLiteDatabase;
        Date date = new Date();

        String datePattern = "dd-MM-yyyy";
        SimpleDateFormat datePatternFormat = new SimpleDateFormat(datePattern);

        String timePattern = "hh:mm a";
        SimpleDateFormat timePatternFormat = new SimpleDateFormat(timePattern);




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_route_report_genarate);

            reportHelper = new ReportHelper(this );
            sqLiteDatabase = reportHelper.getWritableDatabase();
            callFindViewByIdes();
            callonClickListener();

        }

        private void callonClickListener() {
            saveAndPrintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String driverName = String.valueOf(editTextName.getText());
                    String contactNo = String.valueOf(editTextPhone.getText());
                    String item = spinner.getSelectedItem().toString();
                    int qty = Integer.parseInt(String.valueOf(editTextQty.getText()));
                    int amount = qty*itemPrice[spinner.getSelectedItemPosition()];
                    reportHelper.insert(driverName,contactNo,date.getTime(),item,qty,amount);

                    printInvoice();


                }
            });

        }

        private void printInvoice() {
            PdfDocument myPdfDocument = new PdfDocument();
            Paint myPaint = new Paint();

            String[] columns = {"invoiceNo","driverName","contactNo","date","item","qty","amount"};
            Cursor cursor = sqLiteDatabase.query("myTable",columns,null,null,null,null,null);
            cursor.move(cursor.getCount());
            //pdf document
            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1000,900,1).create();
            PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
            Canvas canvas = myPage.getCanvas();

            myPaint.setTextSize(80);
            canvas.drawText("WasteMaster",30,80,myPaint);

            myPaint.setTextSize(30);
            canvas.drawText("136 Maharagama Vijerama,Colombo",30,120,myPaint);

            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Invoice No",canvas.getWidth()-40,40,myPaint);
            canvas.drawText(String.valueOf(cursor.getInt(0)),canvas.getWidth()-40,80,myPaint);
            myPaint.setTextAlign(Paint.Align.LEFT);

            myPaint.setColor(Color.rgb(150,150,150));
            canvas.drawRect(30,150,canvas.getWidth()-30,160,myPaint);

            myPaint.setColor(Color.BLACK);
            canvas.drawText("Date",50,200,myPaint);
            canvas.drawText(datePatternFormat.format(cursor.getLong(3)),250,200,myPaint);

            canvas.drawText("Time",620,200,myPaint);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(timePatternFormat.format(cursor.getLong(3)),canvas.getWidth()-40,200,myPaint);
            myPaint.setTextAlign(Paint.Align.LEFT);

            myPaint.setColor(Color.rgb(150,150,150));
            canvas.drawRect(30,250,250,300,myPaint);

            myPaint.setColor(Color.WHITE);
            canvas.drawText("Salary To:",50,285,myPaint);

            myPaint.setColor(Color.BLACK);
            canvas.drawText("Driver Name: ",30,350,myPaint);
            canvas.drawText(cursor.getString(1),280,350,myPaint);

            canvas.drawText("Phone#",620,350,myPaint);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(cursor.getString(2),canvas.getWidth()-40,350,myPaint);
            myPaint.setTextAlign(Paint.Align.LEFT);

            myPaint.setColor(Color.rgb(150,150,150));
            canvas.drawRect(30,400,canvas.getWidth()-30,450,myPaint);

            myPaint.setColor(Color.WHITE);
            canvas.drawText("Month",50,435,myPaint);
            canvas.drawText("basic",550,435,myPaint);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Amount",canvas.getWidth()-40,435,myPaint);
            myPaint.setTextAlign(Paint.Align.LEFT);

            myPaint.setColor(Color.BLACK);
            canvas.drawText(cursor.getString(4),50,400,myPaint);
            canvas.drawText(String.valueOf(cursor.getInt(5)),550,480,myPaint);
            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(String.valueOf(cursor.getInt(6)),canvas.getWidth()-40,480,myPaint);
            myPaint.setTextAlign(Paint.Align.RIGHT);

            myPaint.setColor(Color.rgb(150,150,150));
            canvas.drawRect(30,550,canvas.getWidth()-30,560,myPaint);

            myPaint.setColor(Color.BLACK);
            canvas.drawText("SUBTOTAL",550,600,myPaint);
            canvas.drawText("Bonus",550,640,myPaint);
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            canvas.drawText("ToTAL",550,680,myPaint);
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));

            myPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(String.valueOf(cursor.getInt(6)),970,600,myPaint);
            canvas.drawText(String.valueOf(cursor.getInt(6)*4/100),970,640,myPaint);
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            canvas.drawText(String.valueOf(cursor.getInt(6)+(cursor.getInt(6)*4/100)),970,680,myPaint);

            myPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Make all checks payable to \"WasteMaster Services\"",30,800,myPaint);
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));

            canvas.drawText("Thank you Very Much Come Back Again",30,840,myPaint);

            myPdfDocument.finishPage(myPage);
            File file = new File(this.getExternalFilesDir("/"),cursor.getInt(0)+ " WasteMaster.pdf");

            try {
                myPdfDocument.writeTo(new FileOutputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            myPdfDocument.close();

        }

        private void callFindViewByIdes(){
            saveAndPrintButton = findViewById(R.id.btnSaveAndPrint);

            editTextName = findViewById(R.id.editTextName);
            editTextPhone = findViewById(R.id.editTextPhone);
            editTextQty = findViewById(R.id.editTextQty);
            spinner = findViewById(R.id.spinner);
            itemList = new String[]{"Malabe","Maharagama","Nugegoda","Pamunuwa","Moratuwa","Pannipitiya","Kottawa"};
            itemPrice = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,itemList);
            spinner.setAdapter(adapter);

        }
    }

