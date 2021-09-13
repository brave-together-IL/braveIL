package com.brave.registration.regist.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.brave.registration.regist.app.repositories.TokenRepository;

public class TokenViewModel extends ViewModel {

    private TokenRepository tokenRepository;

    public TokenViewModel() {
        tokenRepository = TokenRepository.getInstance();
    }

    public LiveData<String> getLDToken() {
        return tokenRepository.getLDToken();
    }

    public void getTokenApi(String auth) {
        tokenRepository.getTokenApi(auth);
    }
}
