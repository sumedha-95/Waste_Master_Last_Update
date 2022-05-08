package com.example.waste_master_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waste_master_two.Database.CreateDB;
import com.example.waste_master_two.Database.DBHandler;

public class CreateBin extends AppCompatActivity {
    EditText city,lordtype,cleaningperoid,location;
    Button createbin,openmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bin);

        createbin = findViewById(R.id.editBinbutton);
        openmap = findViewById(R.id.deleteBin);
        city =findViewById(R.id.tx_name);
        lordtype = findViewById(R.id.tx2_name);
        cleaningperoid = findViewById(R.id.tx3_name);
        location = findViewById(R.id.location);

        createbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateDB createDB  = new CreateDB(getApplicationContext());
                long newID = createDB.addInfo(city.getText().toString(),lordtype.getText().toString(),
                        cleaningperoid.getText().toString(),location.getText().toString());
                Toast.makeText(CreateBin.this, "Successfully Add Bin. ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),EditBin.class);
                startActivity(i);
                city.setText(null);
                lordtype.setText(null);
                cleaningperoid.setText(null);
                location.setText(null);

            }
        });

        //open map
        openmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:6.927079"));
                Intent chooser = Intent.createChooser(intent,"Launch Maps");
                startActivity(chooser);
            }
        });


    }
}