package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.okta.oidc.Okta;

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
            navigateToHome();
        } else {
            navigateToLogin();
        }
    }
//j
    private void navigateToHome(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void navigateToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}