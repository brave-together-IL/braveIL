package com.brave.registration.regist.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.brave.registration.regist.app.repositories.UserRepository;
import com.brave.registration.regist.app.response.UserResponse;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = UserRepository.getInstance();
    }

    public LiveData<UserResponse> getLDUser() { return userRepository.getLDUser();}
}
