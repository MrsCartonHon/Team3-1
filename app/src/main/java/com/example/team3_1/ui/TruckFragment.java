package com.example.team3_1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.MainActivity;
import com.example.team3_1.MangerHomeActivity;
import com.example.team3_1.R;
/*import com.example.team3_1.SQLite.DBManager;
import com.example.team3_1.SQLite.DatabaseHelper;*/
import com.example.team3_1.TruckItem;
import com.example.team3_1.TruckListAdapter;
import com.example.team3_1.data.Truck;
import com.example.team3_1.data.TruckViewModel;
import com.example.team3_1.formActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TruckFragment extends Fragment {
    private Intent intent;

    public TruckFragment() {
        super(R.layout.truck_fragment);
    }
    private ArrayList<TruckItem> mTruckList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Menu fabMenu;
    private FloatingActionButton buttonInsert;
    private View view;
    private TruckViewModel truckViewModel;


    /*private DBManager dbManager;
    final String[] from = new String[] {
            DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.TASK
    };*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*dbManager = new DBManager(this.getContext());
        dbManager.open();*/


        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.truck_fragment, container, false);
        createTruckList();
        buildRecyclerView(view);
        final TruckListAdapter adapter = new TruckListAdapter(mTruckList);
        truckViewModel = ViewModelProviders.of(getActivity()).get(TruckViewModel.class);

        truckViewModel.getAllTrucks().observe((LifecycleOwner) mTruckList, new Observer<ArrayList<Truck>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<Truck> trucks) {
                adapter.setTruck(trucks);
            }

        });

        buttonInsert = (FloatingActionButton) view.findViewById(R.id.fab);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });


        return view;
    }




    public void showPopup(View view) {
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

    public void showMoreOptions(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.edit_truck, popup.getMenu());
        popup.show();
    }

    public void buildRecyclerView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        /*if(dbManager.getRowCount() > 0) {
            mTruckList = dbManager.setUpTrucks();
        }*/
        mAdapter = new TruckListAdapter(mTruckList);



        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void updateItem(int position) {
        //NEED TO GET NAME AND TASK FROM DATA BASE AND UPDATE THE TRUCK
    }

    public void insertTruck(int position) {
        //updateItem(position);
        //TruckItem currentItem = mTruckList.get(position);

        mTruckList.add(position, new TruckItem("Truck " + (position + 1), "Going to Grain Cart"));
        mAdapter.notifyDataSetChanged();
    }
    public void createTruckList(){
        mTruckList = new ArrayList<>();
        //mTruckList.add(new TruckItem("Truck 1",R.drawable.more_options_icon, "Going to grain cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
    }


    public void moreOptionsTruck(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rename_truck:
                Log.d("RENAME", "RENAME");
            case R.id.delete_truck:
                Log.d("DELETE", "DELETE");
        }
    }
    public void AddTruck(MenuItem item) {
        int position = mRecyclerView.getAdapter().getItemCount();
        insertTruck(position);
        Log.d("ADD","TRUCK ADDED");
        startNewActivity(formActivity.class);
    }

    private void startNewActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
        //finish();
    }



}
