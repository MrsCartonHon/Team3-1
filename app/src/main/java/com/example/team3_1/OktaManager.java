package com.example.team3_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.okta.oidc.*;
import com.okta.oidc.clients.sessions.SessionClient;
import com.okta.oidc.clients.web.WebAuthClient;
import com.okta.oidc.net.response.UserInfo;
import com.okta.oidc.storage.SharedPreferenceStorage;
import com.okta.oidc.storage.security.DefaultEncryptionManager;
import com.okta.oidc.util.AuthorizationException;

import java.util.concurrent.Executors;

import javax.security.auth.callback.Callback;

public class OktaManager {
    private WebAuthClient client;
    private  SessionClient sessionClient;

    public OktaManager(Context context) {
        OIDCConfig config = new OIDCConfig.Builder()
                .clientId("0oab1skuceDbCZwrI5d6")
                .redirectUri("com.okta.dev-7036123:/login")
                .endSessionRedirectUri("com.okta.dev-7036123:/logout")
                .scopes("openid", "profile", "offline_access")
                .discoveryUri("https://dev-7036123.okta.com")
                .create();
        client = new Okta.WebAuthBuilder()
                .withConfig(config)
                .withContext(context.getApplicationContext())
                .withStorage(new SharedPreferenceStorage(context))
                .withCallbackExecutor(null)
                .setRequireHardwareBackedKeyStore(false)
                .withTabColor(context.getResources().getColor(R.color.primaryColor))
                .supportedBrowsers("com.android.chrome", "org.mozilla.firefox")
                .create();
        sessionClient = client.getSessionClient();
    }


    public boolean isAuthenticated() {
        return sessionClient.isAuthenticated();
    }

    public void registerWebAuthCallback(ResultCallback callback, Activity activity) {
        client.registerCallback(callback, activity);
    }

    public void registerUserProfileCallback(RequestCallback<UserInfo, AuthorizationException> callback) {
        sessionClient.getUserProfile(callback);
    }

    public void signIn(Activity activity, AuthenticationPayload payload) {
        client.signIn(activity, payload);
    }

    public void logOut(Activity activity, RequestCallback<Integer,AuthorizationException> callback) {
        client.signOut(activity, callback);
    }

    public void clearUserData() {

        String accessToken = null;
        try {
            Tokens token = sessionClient.getTokens();
            accessToken = token.getAccessToken();
        } catch (AuthorizationException e) {
            //handle error
        }

        if (accessToken != null) {
            //use access token
            sessionClient.revokeToken(accessToken,new RequestCallback<Boolean, AuthorizationException>() {
                @Override
                public void onSuccess(@NonNull Boolean result) {
                    // Token was revoked
                    Log.d("OktaManager", " " + result);

                }
                @Override
                public void onError(String error, AuthorizationException exception) {
                    // An error occurred
                    Log.d("OktaManager", " " + exception);
                }
            });

        }

    }



}
