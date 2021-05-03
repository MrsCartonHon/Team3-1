package com.example.team3_1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team3_1.R;

public class addTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText task = (EditText) findViewById(R.id.task);
        Button Save =  findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                //check if Text is entered into all data fields. and send error if not filled in.
                if( TextUtils.isEmpty(task.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String taskName = task.getText().toString();


                    replyIntent.putExtra("task_task", taskName);



                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}