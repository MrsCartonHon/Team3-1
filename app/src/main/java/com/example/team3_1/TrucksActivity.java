package com.example.team3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.okta.oidc.AuthenticationPayload;
import com.okta.oidc.AuthorizationStatus;
import com.okta.oidc.RequestCallback;
import com.okta.oidc.ResultCallback;
import com.okta.oidc.util.AuthorizationException;

import com.leinardi.android.speeddial.SpeedDialActionItem;

import java.util.LinkedList;

public class TrucksActivity extends AppCompatActivity {

//    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucks);
        Button signOutBtn = findViewById(R.id.signOutBtn);
        Activity thisclass = this;
        signOutBtn.setOnClickListener(v -> {
            //MainActivity.oktaManager.signOut(thisclass);
            MainActivity.oktaManager.logOut(this, signOutCallback(thisclass));

        });
//        for(int i = 0; i<20; i++) {
//            mWordList.addLast("Word" + i);
//        }
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
