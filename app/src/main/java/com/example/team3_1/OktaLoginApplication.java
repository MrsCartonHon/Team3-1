package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

public class OktaLoginApplication extends Application {
    private OktaManager oktaManager;
    @Override
    public void onCreate() {
        super.onCreate();
        oktaManager = new OktaManager(this);
    }
}