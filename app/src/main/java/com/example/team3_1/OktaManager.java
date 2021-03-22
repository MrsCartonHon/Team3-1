package com.example.team3_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

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
    private final WebAuthClient client;
    private final SessionClient sessionClient;

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
                .withTabColor(Color.BLUE)
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

    public void signOut(Activity activity, RequestCallback callback) {
        client.signOut(activity, callback);
    }

    public void clearUserData() {
        sessionClient.clear();
    }

}
