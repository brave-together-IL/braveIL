package com.brave.registration.regist.app.apiclients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.MainActivity;
import com.brave.registration.regist.app.mock.MockDB;
import com.brave.registration.regist.app.models.User;
import com.brave.registration.regist.app.response.TokenResponse;
import com.brave.registration.regist.app.response.UserResponse;
import com.brave.registration.regist.app.utils.API_MODE;
import com.brave.registration.regist.app.utils.AppExecutors;
import com.brave.registration.regist.app.utils.RetrofitService;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class LoginApiClient {
    private static LoginApiClient instance;

    private MutableLiveData<User> ldUser;

    private LoginApiClient.GetLoginRunnable getLoginRunnable;

    private MockDB mockDB;

    public static LoginApiClient getInstance() {
        if ( instance == null) {
            instance = new LoginApiClient();
        }
        return instance;
    }

    private LoginApiClient() {

        this.ldUser = new MutableLiveData<>();
        if ( MainActivity.apiMode == API_MODE.DEV_MOCK) {
            mockDB = MockDB.getInstance();
        }
    }

    public LiveData<User> getLDUser() {
        return ldUser;
    }

    public void login(String user, String password) {
        if ( getLoginRunnable!=null) {
            getLoginRunnable = null;
        }

        getLoginRunnable = new LoginApiClient.GetLoginRunnable(user, password);

        final Future loginHandler= AppExecutors.getInstance().networkIO().submit(getLoginRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {

            @Override
            public void run() {
                loginHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    private class GetLoginRunnable implements Runnable {

        private String user;
        private String password;
        boolean cancelRequest;

        public GetLoginRunnable(String user, String password) {
            this.user = user;
            this.password = password;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            if (MainActivity.apiMode == API_MODE.DEV_MOCK) {
                loginMockUser(user,password);
            }
            try {
                if ( cancelRequest) {
                    return;
                }

                Response<UserResponse> response = login(user, password).execute();
                if ( response.code() == 200) {
                    UserResponse userResponse = response.body();
                    User user = new User(userResponse);
                    ldUser.postValue(user);
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    ldUser.postValue(null);
                }
            } catch ( IOException e) {
                Log.v("TOKEN_API", "failed getting token");
                e.printStackTrace();
                ldUser.postValue(null);
            }
        }

        private void loginMockUser(String username, String password) {
            User user = mockDB.login(username, password);
            ldUser.postValue(user);
        }

        private Call<UserResponse> login(String user, String password) {
            return RetrofitService.getBraveApi()
                    .login(user, password);
        }
    }
}
