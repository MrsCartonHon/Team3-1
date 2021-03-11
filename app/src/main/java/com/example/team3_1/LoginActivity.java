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
import com.okta.oidc.ResultCallback;
import com.okta.oidc.Tokens;
import com.okta.oidc.util.AuthorizationException;

public class LoginActivity extends AppCompatActivity {
    //private OktaManager oktaManager =  OktaLoginApplication.oktaManager;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button)findViewById(R.id.signInBtn);
        Activity thisclass = this;
        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AuthenticationPayload payload = new AuthenticationPayload.Builder()
                        .build();
                OktaLoginApplication.oktaManager.signIn(thisclass, payload);
            }
        });
        setupOktaCallback();
        setupViews();
    }
    private void setupOktaCallback(){
        OktaLoginApplication.oktaManager.registerWebAuthCallback(getAuthCallback(), this);
    }
    private void setupViews(){

    }
    private ResultCallback getAuthCallback(){
        ResultCallback object = new ResultCallback<AuthorizationStatus, AuthorizationException>() {
            @Override
            public void onSuccess(@NonNull AuthorizationStatus status){
                if (status == AuthorizationStatus.AUTHORIZED){
                    navigateToHome();
                } else if (status == AuthorizationStatus.SIGNED_OUT){
                    //clear session
                }
            }
            @Override
            public void onCancel(){
                Log.d("LoginActivity", "Canceled");
            }
            @Override
            public void onError(@NonNull String msg, AuthorizationException error){
                Log.d("LoginActivity", "Error: $msg");
            }
        };
        return  object;
    }

    private void navigateToHome(){

    }

    public void onSignIn(View view){

    }



}
