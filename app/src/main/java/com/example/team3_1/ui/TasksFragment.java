package com.example.team3_1.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.team3_1.ui.TaskAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.team3_1.R;
import com.example.team3_1.TaskDb.Task;
import com.example.team3_1.TaskDb.TaskViewModel;
import com.example.team3_1.TruckDb.Truck;
import com.example.team3_1.TruckDb.TruckViewModel;
import com.example.team3_1.TruckListAdapter;
import com.example.team3_1.formActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TasksFragment extends Fragment implements TaskAdapter.OnTaskDelete {
    private static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton buttonInsert;
    private TaskViewModel mTaskViewModel;
    private List<Task> mTaskList;

    public TasksFragment() {
        super(R.layout.tasks_fragment);
        //change change
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createTaskList();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tasks_fragment, container, false);
        buildRecyclerView(view);
        buttonInsert = view.findViewById(R.id.fab);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  int position = Integer.parseInt(editTextInsert.getText().toString());
                showFabPopUp(v);

            }
        });

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTaskViewModel.getAllTask().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setTasks(tasks);
            }
        });

        return view;

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Task task = new Task(data.getStringExtra("task_task"), data.getStringExtra("truck_name"));
            mTaskViewModel.insert(task);
        } else {
            Toast.makeText(getContext(), R.string.new_truck_error_message, Toast.LENGTH_LONG).show();
        }
    }
    public void addTask(MenuItem item) {
        Intent intent = new Intent(getContext(), addTaskActivity.class);
        startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);

    }

    public void buildRecyclerView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new TaskAdapter(mTaskList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createTaskList() {
        mTaskList = new ArrayList<>();

    }

    public void showFabPopUp(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_new_task:
                      addTask(item);

                        return true;
                    default:
                        return true;
                }
            }
        });
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.newtaskfab, popup.getMenu());
        popup.show();
    }

    @Override
    public void deleteTask(Task task) {
        mTaskViewModel.deleteTask(task);
    }
}

