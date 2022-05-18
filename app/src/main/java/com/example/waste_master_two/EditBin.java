package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waste_master_two.Database.CreateDB;

import java.util.List;

public class EditBin extends AppCompatActivity {
    //create link with xml file
    EditText city,lordtype,cleaningperoid,location;
    Button editbin,deletebin,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bin);

        //create link with xml
        city = findViewById(R.id.editBinText);
        lordtype = findViewById(R.id.editBinlord);
        cleaningperoid = findViewById(R.id.editbinClening);
        location = findViewById(R.id.editBinlocation);

        //create link with xml - button
        editbin = findViewById(R.id.editBin1);
        deletebin = findViewById(R.id.deletebin1);
        search = findViewById(R.id.searchcity);

        //search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateDB createDB = new CreateDB(getApplicationContext());
                List user = createDB.readAllInfo(city.getText().toString());

                //check condition
                if (user.isEmpty()){
                    Toast.makeText(EditBin.this, "No User", Toast.LENGTH_SHORT).show();
                    city.setText(null);
                }
                else {

                    Toast.makeText(EditBin.this, "User Found! User: "+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    city.setText(user.get(0).toString());
                    lordtype.setText(user.get(1).toString());
                    cleaningperoid.setText(user.get(2).toString());
                    location.setText(user.get(3).toString());

                }

            }
        });

        //edit bin button
        editbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateDB createDB = new CreateDB(getApplicationContext());
                //check with boolean value
                Boolean status = createDB.updateInfo(city.getText().toString(),lordtype.getText().toString(),
                        cleaningperoid.getText().toString(),location.getText().toString());

                //check condition
                if (status){
                    Toast.makeText(EditBin.this, "Bin Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditBin.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //delete bin button
        deletebin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateDB createDB = new CreateDB(getApplicationContext());
                createDB.deleteInfo(city.getText().toString());

                //Toast massage
                Toast.makeText(EditBin.this, "Bin Deleted", Toast.LENGTH_SHORT).show();

                city.setText(null);
                lordtype.setText(null);
                cleaningperoid.setText(null);
                location.setText(null);
            }
        });

    }
}