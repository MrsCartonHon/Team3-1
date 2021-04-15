package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;


import com.example.team3_1.ui.MapFragment;
import com.example.team3_1.ui.TasksFragment;
import com.example.team3_1.ui.TruckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MangerHomeActivity extends AppCompatActivity {
//    private final LinkedList<String> mWordList = new LinkedList<>();
    private ArrayList<TruckItem> mTruckList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Menu fabMenu;
    private FloatingActionButton buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_manager_home); //trucks_toolbar
        FragmentManager  fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment, TruckFragment.class, null)
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Trucks");
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                     switch (item.getItemId()) {
                         case R.id.trucks:
                             fragmentManager.beginTransaction()
                                     .setReorderingAllowed(true)
                                     .replace(R.id.fragment, TruckFragment.class, null)
                                     .commit();
                             toolbar.setTitle("Trucks");
                             return true;
                         case R.id.map:
                             fragmentManager.beginTransaction()
                                     .setReorderingAllowed(true)
                                     .replace(R.id.fragment, MapFragment.class, null)
                                     .commit();
                             toolbar.setTitle("Map");
                             return true;
                         case R.id.tasks:
                             fragmentManager.beginTransaction()
                                     .setReorderingAllowed(true)
                                     .replace(R.id.fragment, TasksFragment.class, null)
                                     .commit();
                             toolbar.setTitle("Tasks");
                             return true;
                     }
                     return true;
                 }
             });

    }




    //Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startNewActivity(SettingsActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void startNewActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        //finish();
    }

}
