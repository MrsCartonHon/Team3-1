package com.example.team3_1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.team3_1.R;

public class TasksFragment extends Fragment {
    public TasksFragment() {
        super(R.layout.tasks_fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.tasks_fragment, container, false);
        return view;
    }
}
