package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.okta.oidc.AuthenticationPayload;
import com.okta.oidc.AuthorizationStatus;
import com.okta.oidc.RequestCallback;
import com.okta.oidc.ResultCallback;
import com.okta.oidc.util.AuthorizationException;

import com.leinardi.android.speeddial.SpeedDialActionItem;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrucksActivity extends AppCompatActivity {
    private ArrayList<TruckItem> mTruckList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Menu fabMenu;

    private FloatingActionButton buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucks);

        createTruckList();
        buildRecyclerView();

        buttonInsert = (FloatingActionButton) findViewById(R.id.fab);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        Button signOutBtn = findViewById(R.id.signOutBtn);
        Activity thisclass = this;
        signOutBtn.setOnClickListener(v -> {
            //MainActivity.oktaManager.signOut(thisclass);
            MainActivity.oktaManager.logOut(this, signOutCallback(thisclass));

        });
    }

    public void insertTruck(int position) {
        mTruckList.add(position, new TruckItem("Truck " + (position+1), R.drawable.more_options_icon, "Going to Grain Cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
        mAdapter.notifyItemInserted(position);
    }

    public void createTruckList(){
        mTruckList = new ArrayList<>();
        mTruckList.add(new TruckItem("Truck 1", R.drawable.more_options_icon, "Going to grain cart", "3:45", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TruckListAdapter(mTruckList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private RequestCallback<Integer, AuthorizationException> signOutCallback(Activity activity) {
        RequestCallback object = new RequestCallback() {
            @Override
            public void onSuccess(@NonNull Object result) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String error, Exception exception) {
                Log.d("DriverHomePage", "Logout error");
            }

        };
        return object;
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();
    }
    public void showMoreOptions(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.edit_truck, popup.getMenu());
        popup.show();
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
