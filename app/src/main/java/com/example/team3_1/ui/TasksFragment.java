package com.example.team3_1.ui;

import android.os.Bundle;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import androidx.fragment.app.Fragment;

import com.example.team3_1.R;


public class TasksFragment extends Fragment {
    private ArrayList<TaskItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    public TasksFragment() {
        super(R.layout.tasks_fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createTaskList();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.tasks_fragment, container, false);
        buildRecyclerView(view);
        buttonInsert = view.findViewById(R.id.button_insert);
        buttonRemove = view.findViewById(R.id.button_remove);
        editTextInsert = view.findViewById(R.id.edittext_insert);
        editTextRemove = view.findViewById(R.id.edittext_remove);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  int position = Integer.parseInt(editTextInsert.getText().toString());
                int position = 0;
                insertItem(position);

            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int position = Integer.parseInt(editTextRemove.getText().toString());
                //     int position = (int) Collections.max(mExampleList);
                if (mExampleList.size() > 0)
                {
                    int position = mExampleList.size();
                    removeItem(position-1);
                }

            }
        });
        return view;

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
        mExampleList.add(new TaskItem( "Line 1", "Line 2"));
        mExampleList.add(new TaskItem( "Line 3", "Line 4"));
        mExampleList.add(new TaskItem( "Line 5", "Line 6"));
    }
    public void insertItem(int position) {
        mExampleList.add(position, new TaskItem( "New Item At Position" + position, "This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
