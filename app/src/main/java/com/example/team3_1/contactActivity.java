package com.example.team3_1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class contactActivity extends AppCompatActivity {
private Button backTrucks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("driver_name"));
        phoneNumber.setText(intent.getStringExtra("phone_number"));


        backTrucks = findViewById(R.id.button);
        backTrucks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
    public void openTrucksActivity(){
        Intent intent = new Intent(this, MangerHomeActivity.class);
        startActivity(intent);
    }

}