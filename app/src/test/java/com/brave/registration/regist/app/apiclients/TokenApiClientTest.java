package com.brave.registration.regist.app.apiclients;

import static org.junit.Assert.*;

import android.util.Log;

import com.brave.registration.regist.app.models.User;
import com.brave.registration.regist.app.response.TokenResponse;
import com.brave.registration.regist.app.utils.RetrofitService;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;

public class TokenApiClientTest {

    @Ignore
    @Test
    public void getUserNotNull() {
        Call<User> token = RetrofitService.getBraveTestApi()
                .getUser("1");

        try {
            Response<User> execute = token.execute();
            assertNotNull(execute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTokenNotNull() {
        String auth = Credentials.basic("one@brave.com", "1234512345");
        Call<TokenResponse> callToken = RetrofitService.getBraveTestApi().getToken(auth);
        try {
            Response<TokenResponse> response = callToken.execute();
            assertNotNull(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}