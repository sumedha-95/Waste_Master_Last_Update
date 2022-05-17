package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RouteReport extends AppCompatActivity {

    Button SaveandPrint,PrientButton;
    EditText  edittextRoute,edittextroute2,edittextlocation;
    Spinner spinner;

    String[] RouteList;
    int[] RouteNumber;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_report);

        callFindViewByIdes();
    }

    private void callFindViewByIdes() {
        SaveandPrint = findViewById(R.id.report);
        PrientButton = findViewById(R.id.buttonPrint);

        edittextRoute = findViewById(R.id.edittextRoute);
        edittextroute2 = findViewById(R.id.edittextroute2);
        edittextlocation = findViewById(R.id.edittextlocation);

        spinner = findViewById(R.id.spinner);

        RouteList = new String[]{"Maharagama","Boralesgamuwa","Nugegoda","Pamunuwa","Pannipitiya","Kottawa","Malabe"};
        RouteNumber = new int[]{1,4,5,7,2,3,5} ;
        adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,RouteList);
        spinner.setAdapter(adapter);



    }
}