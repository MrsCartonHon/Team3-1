package com.example.team3_1.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.team3_1.TruckDb.Truck;
import com.example.team3_1.TruckDb.TruckViewModel;
import com.example.team3_1.TruckListAdapter;

import java.util.ArrayList;
import java.util.List;

public class addTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    TruckViewModel mTruckViewModel;
    RecyclerView recyclerView;
    TruckListAdapter mAdapter;
    private List<String> trucksNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        spinner = (Spinner) findViewById(R.id.spinner);
        EditText task = (EditText) findViewById(R.id.task);
        Button Save =  findViewById(R.id.Save);
        recyclerView = findViewById(R.id.recyclerView);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

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

    private void loadSpinnerData() {
        trucksNameList = new ArrayList<>();
        mTruckViewModel = ViewModelProviders.of(this).get(TruckViewModel.class);
        mTruckViewModel.getAllTrucks().observe(this, new Observer<List<Truck>>() {
            @Override
            public void onChanged(@Nullable final List<Truck> trucks) {
                for(Truck truck : trucks) {
                    trucksNameList.add(truck.getName());
                }
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trucksNameList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String label = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "You selected: " + label + "at position: " + position, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}