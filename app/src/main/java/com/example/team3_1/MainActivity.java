package com.example.team3_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.maps.Style;
import com.okta.oidc.RequestCallback;
import com.okta.oidc.net.response.UserInfo;
import com.okta.oidc.util.AuthorizationException;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static OktaManager oktaManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (oktaManager == null){
            oktaManager = new OktaManager(this);
        }
        //setContentView(R.layout.activity_main);

        if(oktaManager.isAuthenticated()){
            getUserProfile();
        } else {
            navigateToLogin();
        }
    }

    private void navigateToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void getUserProfile(){
        MainActivity.oktaManager.registerUserProfileCallback(new RequestCallback<UserInfo, AuthorizationException>() {
            @Override
            public void onSuccess(@NonNull UserInfo result) {
                boolean isManager = (boolean) result.get("isTruckManager");
                navigateToHome(isManager);
            }

            @Override
            public void onError(String error, AuthorizationException exception) {
                Log.d("LoginActivity", "error");
                navigateToLogin();
            }
        });
    }

    private void navigateToHome(boolean isManager){
        Intent intent;
        if(isManager){
            intent = new Intent(this, MangerHomeActivity.class);
        } else {
            intent = new Intent(this, DriverHomePage.class);
        }
        startActivity(intent);
        finish();
    }








}