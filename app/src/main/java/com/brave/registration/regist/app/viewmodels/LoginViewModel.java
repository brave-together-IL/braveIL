package com.brave.registration.regist.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = LoginRepository.getInstance();
    }

    public LiveData<User> getLDLogin() {
        return loginRepository.getLDUser();
    }

    public void login(String user, String password) {
        loginRepository.login(user, password);
    }
}