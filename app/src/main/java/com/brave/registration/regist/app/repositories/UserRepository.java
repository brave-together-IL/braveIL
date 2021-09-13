package com.brave.registration.regist.app.repositories;

import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.apiclients.UserApiClient;
import com.brave.registration.regist.app.models.SignupUser;
import com.brave.registration.regist.app.models.User;
import com.brave.registration.regist.app.response.UserResponse;

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

    public LiveData<UserResponse> getLDUser() {
        return userApiClient.getLDUser();
    }

    public void getUserFromApi(String id) {
        userApiClient.getUser(id);
    }

    public LiveData<UserResponse> getLDSignupUser() {
        return userApiClient.getLDSignupUser();
    }

    public void signupUser(SignupUser user) {
        userApiClient.signupUser(user);
    }
}
