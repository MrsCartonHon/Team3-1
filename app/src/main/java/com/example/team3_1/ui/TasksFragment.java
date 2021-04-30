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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import com.example.team3_1.R;
import com.example.team3_1.formActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TasksFragment extends Fragment {
    private static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;
    private ArrayList<TaskItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;
    private FloatingActionButton buttonInsert;

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

        return view;

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            TaskItem task = new TaskItem(data.getStringExtra("task_task"),"text");
            mExampleList.add(position, new TaskItem("Task", string));
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
        mAdapter = new TaskAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createTaskList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new TaskItem("Task ", "Go harvest grain"));
        mExampleList.add(new TaskItem("Task ", "Refuel the combine"));
        mExampleList.add(new TaskItem("Task ", "Fertilize field 3"));
    }

    public void insertItem(int position) {
        String string = editTextInsert.getText().toString();
        mExampleList.add(position, new TaskItem("Task", string));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void showFabPopUp(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_new_task:
                      //  addTask(item);

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

}

