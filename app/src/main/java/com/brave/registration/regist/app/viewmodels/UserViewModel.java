package com.brave.registration.regist.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getUser() {
        return userRepository.getUser();
    }

    public void registerUser(User user) {
        userRepository.registerUser(user);
    }
}
