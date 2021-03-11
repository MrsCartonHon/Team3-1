package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.okta.oidc.Okta;

public class MainActivity extends AppCompatActivity {
    private OktaManager oktaManager = new OktaLoginApplication(this.getApplicationContext()).oktaManager;
    //private OktaManager oktaManager = new OktaLoginApplication().oktaManager;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(R.layout.activity_main);
        if(oktaManager.isAuthenticated()){
            navigateToHome();
        } else {
            navigateToLogin();
        }

    }

    private void navigateToHome(){

    }

    private void navigateToLogin(){
        Log.d("MainActivity", "navigateToLogin");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}