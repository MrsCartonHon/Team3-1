package com.example.team3_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.team3_1.ui.MapFragment;
import com.example.team3_1.ui.TasksFragment;
import com.example.team3_1.ui.TruckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MangerHomeActivity extends AppCompatActivity {
    private TruckFragment truckFragment;
    private MapFragment mapFragment;
    private TasksFragment tasksFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_manager_home); //trucks_toolbar
        FragmentManager  fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            truckFragment = new TruckFragment();
            mapFragment = new MapFragment();
            tasksFragment = new TasksFragment();
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
                                     .replace(R.id.fragment, truckFragment, null)
                                     .commit();
                             toolbar.setTitle("Trucks");
                             return true;
                         case R.id.map:
                             fragmentManager.beginTransaction()
                                     .setReorderingAllowed(true)
                                     .replace(R.id.fragment, mapFragment, null)
                                     .commit();
                             toolbar.setTitle("Map");
                             return true;
                         case R.id.tasks:
                             fragmentManager.beginTransaction()
                                     .setReorderingAllowed(true)
                                     .replace(R.id.fragment, tasksFragment, null)
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
