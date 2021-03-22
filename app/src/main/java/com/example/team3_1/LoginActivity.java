package com.example.team3_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.okta.oidc.AuthenticationPayload;
import com.okta.oidc.AuthorizationStatus;
import com.okta.oidc.Okta;
import com.okta.oidc.RequestCallback;
import com.okta.oidc.ResultCallback;
import com.okta.oidc.Tokens;
import com.okta.oidc.net.response.UserInfo;
import com.okta.oidc.util.AuthorizationException;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupOktaCallback();

        Button loginBtn = findViewById(R.id.signInBtn);
        Activity thisclass = this;
        loginBtn.setOnClickListener(v -> {
            AuthenticationPayload payload = new AuthenticationPayload.Builder()
                    .build();
            MainActivity.oktaManager.signIn(thisclass, payload);
        });
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

    private void getUserProfile(){
        MainActivity.oktaManager.registerUserProfileCallback(new RequestCallback<UserInfo, AuthorizationException>() {
            @Override
            public void onSuccess(@NonNull UserInfo result) {
                boolean isManager = (boolean) result.get("isTruckManager");
                navigateToHome(isManager);
            }

            @Override
            public void onError(String error, AuthorizationException exception) {
                Log.d("LoginActivity", error);
            }
        });
    }

    private void navigateToHome(boolean isManager){
        if(isManager){
            Intent intent = new Intent(this, TruckPageActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, DriverHomePage.class);
        }
        finish();
    }



}
