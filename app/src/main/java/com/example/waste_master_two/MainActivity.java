package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Button
    Button userM,RouteM,Manage_Ga,RouteRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link with xml
        userM = findViewById(R.id.bt_id1);
        RouteM = findViewById(R.id.binid11);
        Manage_Ga = findViewById(R.id.Manage_Ga);
        RouteRe = findViewById(R.id.routereport);

        //user management button
        userM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),CleaningActivity2.class);
                startActivity(a);
            }
        });

        //route management button
        RouteM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(a);
            }
        });

        //manage garbage button
        Manage_Ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),ManageGarbage.class);
                startActivity(a);
            }
        });

        //route report button
        RouteRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),RouteReportGenarate.class);
                startActivity(a);
            }
        });
    }
}