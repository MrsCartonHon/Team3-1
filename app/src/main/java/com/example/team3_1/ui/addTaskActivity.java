package com.example.team3_1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.team3_1.R;

public class addTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] trucks = {"item 1", "item 2", "item 3"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Spinner spinner = (Spinner) findViewById(R.id.addTaskTruckDrop);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.trucks,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        EditText task = (EditText) findViewById(R.id.task);
        Button Save =  findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// stopped at the part where constructor is changed


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}