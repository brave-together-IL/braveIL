package com.brave.registration.regist.app.repositories;

import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.apiclients.LoginApiClient;
import com.brave.registration.regist.app.db.entities.User;

public class LoginRepository {
    private static LoginRepository instance;

    private LoginApiClient loginApiClient;

    public static LoginRepository getInstance() {
        if ( instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    private LoginRepository() {
        loginApiClient = LoginApiClient.getInstance();
    }

    public LiveData<User> getLDUser() {
        return loginApiClient.getLDUser();
    }

    public void login(String user, String password) {
        loginApiClient.login(user, password);
    }
}
