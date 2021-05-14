package com.example.team3_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.team3_1.TaskDb.Task;
import com.example.team3_1.TaskDb.TaskViewModel;
import com.example.team3_1.TruckDb.Truck;
import com.example.team3_1.TruckDb.TruckViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class formActivity extends AppCompatActivity {
    private TaskViewModel mTaskViewModel;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        EditText name = (EditText) findViewById(R.id.name);
        EditText contact = (EditText) findViewById(R.id.contact);
        //EditText task = (EditText) findViewById(R.id.task);
        Button Save =  findViewById(R.id.Save);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        List<String> taskNameList = new ArrayList<String>();
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        Activity context = this;
        mTaskViewModel.getAllTask().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                taskList = tasks;
                for(Task task : tasks) {
                    taskNameList.add(task.getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, taskNameList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) context);
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                //check if Text is entered into all data fields. and send error if not filled in.
                if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(contact.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String truckName = name.getText().toString();
                    //String taskName = task.getText().toString();
                    String contactNumber = contact.getText().toString();
                    double latitude = (Math.random() * ((42 - 41) + 1)) + 41;
                    double longitude = (Math.random() * ((-90 + 91) + 1)) - 91;

                    //get Spinner info and update db
                    Task selectedTask = taskList.get(taskNameList.indexOf(spinner.getSelectedItem().toString()));
                    selectedTask.setTruck(truckName);
                    mTaskViewModel.updateTask(selectedTask);


                    replyIntent.putExtra("truck_name", truckName);
                    replyIntent.putExtra("truck_task", selectedTask.getName());
                    replyIntent.putExtra("truck_contact", contactNumber);
                    replyIntent.putExtra("truck_latitude", String.valueOf(latitude));
                    replyIntent.putExtra("truck_longitude", String.valueOf(longitude));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}