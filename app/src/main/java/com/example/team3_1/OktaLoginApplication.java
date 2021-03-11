package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class OktaLoginApplication extends Application {
    public static OktaManager oktaManager;
    public OktaLoginApplication( ) {
        oktaManager = new OktaManager(this.getApplicationContext());
    }
    public OktaLoginApplication(Context context) {
        oktaManager = new OktaManager(context);
    }


}