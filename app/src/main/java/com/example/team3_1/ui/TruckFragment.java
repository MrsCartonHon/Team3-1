package com.example.team3_1.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.R;
import com.example.team3_1.RandomColors;
import com.example.team3_1.TaskDb.Task;
import com.example.team3_1.TaskDb.TaskViewModel;
import com.example.team3_1.TruckDb.Truck;
import com.example.team3_1.TruckDb.TruckViewModel;
import com.example.team3_1.TruckListAdapter;
import com.example.team3_1.formActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TruckFragment extends Fragment implements TruckListAdapter.OnTruckDelete {

    public static final int NEW_TRUCK_ACTIVITY_REQUEST_CODE = 1;

    public TruckFragment() {
        super(R.layout.truck_fragment);
    }
    private List<Truck> mTruckList;
    private RecyclerView mRecyclerView;
    private TruckListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Menu fabMenu;
    private FloatingActionButton buttonInsert;
    private View view;
    private TruckViewModel mTruckViewModel;
    private TaskViewModel mTaskViewModel;
    private RandomColors colorGenerator;
    private List<Task> taskList;
    private List<String> taskNameList = new ArrayList<String>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.truck_fragment, container, false);
        createTruckList();
        buildRecyclerView(view);

        buttonInsert = (FloatingActionButton) view.findViewById(R.id.fab);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFabPopup(v);
            }
        });

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTruckViewModel = ViewModelProviders.of(this).get(TruckViewModel.class);
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTruckViewModel.getAllTrucks().observe(getViewLifecycleOwner(), new Observer<List<Truck>>() {
            @Override
            public void onChanged(@Nullable final List<Truck> trucks) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setTrucks(trucks);
            }
        });
        mTaskViewModel.getAllTask().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                taskList = tasks;
                for(Task task : tasks) {
                    taskNameList.add(task.getName());
                }
            }
        });

        colorGenerator = new RandomColors();

        return view;
    }


    public void showFabPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.menu_new_truck:
                        AddTruck(item);
                        return true;
                    default:
                        return true;
                }
            }
        });
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();
    }

    /*public void showMoreOptions(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.edit_truck, popup.getMenu());
        popup.show();
    }*/

    public void buildRecyclerView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new TruckListAdapter(mTruckList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createTruckList(){
        mTruckList = new ArrayList<>();
        //mTruckList.add(new TruckItem("Truck 1",R.drawable.more_options_icon, "Going to grain cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
    }


    public void AddTruck(MenuItem item) {
        Intent intent = new Intent(getContext(), formActivity.class);
        startActivityForResult(intent, NEW_TRUCK_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void deleteTruck(Truck truck) {
        mTruckViewModel.deleteTruck(truck);
        updateTaskDesc(truck);
    }

    public void updateTaskDesc(Truck truck) {
        Task selectedTask = taskList.get(taskNameList.indexOf(truck.getTask()));
        selectedTask.setTruckNameTask(null);
        mTaskViewModel.updateTask(selectedTask);
    }


    //gets info from the new truck page
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRUCK_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Truck truck = new Truck(data.getStringExtra("truck_name"), data.getStringExtra("truck_task"), data.getStringExtra("truck_contact"), data.getStringExtra("truck_latitude"), data.getStringExtra("truck_longitude"), colorGenerator.getColor());
            mTruckViewModel.insert(truck);
        } else {
            Toast.makeText(getContext(), R.string.new_truck_error_message, Toast.LENGTH_LONG).show();
        }
    }

    public void updateTruck(Truck truck) {
        mTruckViewModel.updateTruck(truck);
    }
}
