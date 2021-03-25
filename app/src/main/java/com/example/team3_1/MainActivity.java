package com.example.team3_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.okta.oidc.AuthenticationPayload;
import com.okta.oidc.AuthorizationStatus;
import com.okta.oidc.Okta;
import com.okta.oidc.RequestCallback;
import com.okta.oidc.ResultCallback;
import com.okta.oidc.net.response.UserInfo;
import com.okta.oidc.util.AuthorizationException;

public class MainActivity extends AppCompatActivity {
    public static OktaManager oktaManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (oktaManager == null){
            oktaManager = new OktaManager(this);
        }
        setContentView(R.layout.activity_main);

        if(oktaManager.isAuthenticated()){
            getUserProfile();
        } else {
            navigateToLogin();
        }
        setupOktaCallback();

        Button signInBtn = findViewById(R.id.signInBtn);
        Activity thisclass = this;
        signInBtn.setOnClickListener(v -> {
            AuthenticationPayload payload = new AuthenticationPayload.Builder()
                    .build();
            MainActivity.oktaManager.signIn(thisclass, payload);
        });
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
                Log.d("MainActivity", exception + "");
            }
        });
    }

    private void navigateToHome(boolean isManager){
        Intent intent;
        if(isManager){
            intent = new Intent(this, TrucksPage.class);
        } else {
            intent = new Intent(this, DriverHomePage.class);
        }
        startActivity(intent);
        finish();
    }

    private void setupOktaCallback(){
        MainActivity.oktaManager.registerWebAuthCallback(getAuthCallback(), this);
    }

    private ResultCallback getAuthCallback(){
        ResultCallback object = new ResultCallback<AuthorizationStatus, AuthorizationException>() {
            @Override
            public void onSuccess(@NonNull AuthorizationStatus status){
                if (status == AuthorizationStatus.AUTHORIZED){
                    Log.d("LoginActivity", "AUTHORIZED");
                    getUserProfile();
                } else if (status == AuthorizationStatus.SIGNED_OUT){
                    //clear session
                    Log.d("LoginActivity", "SIGNED OUT");
                }
            }
            @Override
            public void onCancel(){
                Log.d("LoginActivity", "Canceled");
            }
            @Override
            public void onError(@NonNull String msg, AuthorizationException error){
                Log.d("LoginActivity", "Error: " + error.errorDescription);
            }
        };
        return  object;
    }
}