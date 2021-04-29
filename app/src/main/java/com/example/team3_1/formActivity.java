package com.example.team3_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class formActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        EditText name = (EditText) findViewById(R.id.name);
        EditText contact = (EditText) findViewById(R.id.contact);
        EditText task = (EditText) findViewById(R.id.task);
        Button Save =  findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                //check if Text is entered into all data fields. and send error if not filled in.
                if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(task.getText()) || TextUtils.isEmpty(contact.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String truckName = name.getText().toString();
                    String taskName = task.getText().toString();
                    String contactNumber = contact.getText().toString();
                    replyIntent.putExtra("truck_name", truckName);
                    replyIntent.putExtra("truck_task", taskName);
                    replyIntent.putExtra("truck_contact", contactNumber);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}