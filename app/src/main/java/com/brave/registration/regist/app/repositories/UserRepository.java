package com.brave.registration.regist.app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.request.UserApiClient;

public class UserRepository {

    private static UserRepository instance;

    private UserApiClient userApiClient;


    public static UserRepository getInstance() {
        if ( instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    private UserRepository() {
        userApiClient = UserApiClient.getInstance();
    }

    public LiveData<User> getUser() {
        return userApiClient.getUser();
    }

    public void registerUser(User user) {
        userApiClient.getUser("1");
    }
}
