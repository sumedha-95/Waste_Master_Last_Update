package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CleaningActivity2 extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning2);

        btn1 = findViewById(R.id.binid11);
        btn2 = findViewById(R.id.btact_id2);
        btn3 = findViewById(R.id.btact_id3);
        btn4 = findViewById(R.id.btact_id4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateBin.class);
                startActivity(i);
            }
        });

    }
}