package com.example.waste_master_two;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageGarbage extends AppCompatActivity {

    private String foodandBioqty = "";
    private String plasticandPolitheenqty = "";
    private String glassandnonqty = "";

    //array list
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();

    //layout table
    private TableLayout table;

    //link with xmal
    EditText ed1;
    CheckBox ch1, ch2, ch3;
    Button b1, b2 ,btnsubmitnew1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_garbage);

        //create connection with xml
        ch1 = findViewById(R.id.chk1);
        ch2 = findViewById(R.id.chk2);
        ch3 = findViewById(R.id.chk3);

        ed1 = findViewById(R.id.txtsub);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        btnsubmitnew1 = findViewById(R.id.btnsubmitnew1);

        //move to the create bin page
        btnsubmitnew1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateBin.class);
                startActivity(i);
            }
        });

        //calculate option
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0 ;
                for(int a=0;a<data3.size();a++)
                {
                    sum = sum+Integer.parseInt(data3.get(a).toString());
                }
                ed1.setText(String.valueOf(sum));
            }
        });

        //get sumation
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                for (int a = 0; a < data3.size(); a++) {
                    sum = sum + Integer.parseInt(data3.get(a).toString());
                }
                ed1.setText(String.valueOf(sum));
            }
        });

        //assign value
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Assign();
            }
        });
    }

    //assign value in our array
    public void Assign() {
        if (ch1.isChecked()) {
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            //massage
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Assign Garbage Bin Qty");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("ok", (Dialog, which) -> {

                String foodandBio = ch1.getText().toString();
                int place = 5;

                foodandBioqty = input.getText().toString();

                int tot = place * Integer.parseInt(foodandBioqty);

                data.add(foodandBio);
                data1.add(String.valueOf(place));
                data2.add(String.valueOf(foodandBioqty));
                data3.add(String.valueOf(tot));

                TableLayout table = (TableLayout) findViewById(R.id.tb1);

                String total;

                for (int i = 0; i < data.size(); i++) {
                    String plocation = data.get(i);
                    String prc = data1.get(i);
                    String qty = data2.get(i);
                    total = data3.get(i);

                    t1.setText(plocation);
                    t2.setText(prc);
                    t3.setText(qty);
                    t4.setText(total);

                }

                row.addView(t1);
                row.addView(t2);
                row.addView(t3);
                row.addView(t4);
                table.addView(row);

            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        } else if (ch2.isChecked()) {
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Assign Garbage Bin Qty");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("ok", (Dialog, which) -> {

                String plasticandPolitheen = ch2.getText().toString();
                int place = 10;

                plasticandPolitheenqty = input.getText().toString();

                int tot = place * Integer.parseInt(plasticandPolitheenqty);

                data.add(plasticandPolitheen);
                data1.add(String.valueOf(place));
                data2.add(String.valueOf(plasticandPolitheenqty));
                data3.add(String.valueOf(tot));

                TableLayout table = (TableLayout) findViewById(R.id.tb1);

                String total;

                for (int i = 0; i < data.size(); i++) {
                    String plocation = data.get(i);
                    String prc = data1.get(i);
                    String qty = data2.get(i);
                    total = data3.get(i);

                    t1.setText(plocation);
                    t2.setText(prc);
                    t3.setText(qty);
                    t4.setText(total);

                }

                row.addView(t1);
                row.addView(t2);
                row.addView(t3);
                row.addView(t4);
                table.addView(row);

            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        } else if (ch3.isChecked()) {
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Assign Garbage Bin Qty");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("ok", (Dialog, which) -> {

                String glassandnon = ch3.getText().toString();
                int place = 2;

                glassandnonqty = input.getText().toString();

                int tot = place * Integer.parseInt(glassandnonqty);

                data.add(glassandnon);
                data1.add(String.valueOf(place));
                data2.add(String.valueOf(glassandnonqty));
                data3.add(String.valueOf(tot));

                TableLayout table = (TableLayout) findViewById(R.id.tb1);

                String total;

                for (int i = 0; i < data.size(); i++) {
                    String plocation = data.get(i);
                    String prc = data1.get(i);
                    String qty = data2.get(i);
                    total = data3.get(i);

                    t1.setText(plocation);
                    t2.setText(prc);
                    t3.setText(qty);
                    t4.setText(total);

                }

                row.addView(t1);
                row.addView(t2);
                row.addView(t3);
                row.addView(t4);
                table.addView(row);

            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        }
    }
}