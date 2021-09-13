package com.brave.registration.regist.app.repositories;

import android.media.session.MediaSession;

import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.apiclients.TokenApiClient;

public class TokenRepository {
    private static TokenRepository instance;

    private TokenApiClient tokenApiClient;

    public static TokenRepository getInstance() {
        if ( instance == null) {
            instance = new TokenRepository();
        }
        return instance;
    }

    private TokenRepository() {
        tokenApiClient = TokenApiClient.getInstance();
    }

    public LiveData<String> getLDToken() {
        return tokenApiClient.getLDToken();
    }

    public void getTokenApi(String auth) {
        tokenApiClient.getToken(auth);
    }
}
