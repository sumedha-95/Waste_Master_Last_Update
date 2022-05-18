package com.example.waste_master_two;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.waste_master_two.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    //button initialize
    EditText username, dob, password ;
    Button add, updateProfile;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        //link with xml
        username = findViewById(R.id.etUserNamePM);
        dob = findViewById(R.id.etDobPM);
        password = findViewById(R.id.etPasswordPM);
        add = findViewById(R.id.btnAdd);
        updateProfile = findViewById(R.id.btnUpdateProfile);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);

        //update profile button
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //move to the edit
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        //add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check condition
                if (male.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(username.getText().toString(),
                        dob.getText().toString(),password.getText().toString(),gender);
                //toast massage
                Toast.makeText(ProfileManagement.this, "Route Added. User ID: "+ newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);


            }
        });

    }
}
