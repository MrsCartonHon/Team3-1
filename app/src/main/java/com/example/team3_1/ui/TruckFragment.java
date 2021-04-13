package com.example.team3_1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.R;
import com.example.team3_1.SettingsActivity;
import com.example.team3_1.TruckItem;
import com.example.team3_1.TruckListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TruckFragment extends Fragment {
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
                Log.d("Truck Fragment", "here");
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
        mAdapter = new TruckListAdapter(mTruckList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void insertTruck(int position) {
        mTruckList.add(position, new TruckItem("Truck " + (position+1), R.drawable.more_options_icon, "Going to Grain Cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
        mAdapter.notifyItemInserted(position);
    }
    public void createTruckList(){
        mTruckList = new ArrayList<>();
        mTruckList.add(new TruckItem("Truck 1", R.drawable.more_options_icon, "Going to grain cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
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
        switch (item.getItemId()) {
            case R.id.menu_new_truck:
                int position = mRecyclerView.getAdapter().getItemCount();
                insertTruck(position);

        }
    }
}
