package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;



public class formActivity extends AppCompatActivity {
    EditText name;
    EditText contact;
    EditText color;
    Button Save;
    String n;
    String c;
    String cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        color = (EditText) findViewById(R.id.color);
        Save =  findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = name.getText().toString();
                c = contact.getText().toString();
                cl = color.getText().toString();
               // startNewActivity(MangerHomeActivity.class);
               // finish();
                finish();
            }
        });
    }

    public static String getName(String n)
    {
        return n;
    }
    public static String getTask(String cl)
    {
        return cl;
    }

    private void startNewActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);

    }
}