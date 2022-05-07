package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button userM,RouteM,Manage_Ga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userM = findViewById(R.id.bt_id1);
        RouteM = findViewById(R.id.binid11);
        Manage_Ga = findViewById(R.id.Manage_Ga);

        userM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),CleaningActivity2.class);
                startActivity(a);
            }
        });

        Manage_Ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(),ManageGarbage.class);
                startActivity(a);
            }
        });
    }
}