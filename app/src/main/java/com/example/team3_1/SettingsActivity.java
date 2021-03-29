package com.example.team3_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.okta.oidc.RequestCallback;
import com.okta.oidc.util.AuthorizationException;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        Button signOutBtn = findViewById(R.id.signOutBtn);
        Activity thisclass = this;
        signOutBtn.setOnClickListener(v -> {
            MainActivity.oktaManager.logOut(this, signOutCallback(thisclass));

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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void startNewActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
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

}