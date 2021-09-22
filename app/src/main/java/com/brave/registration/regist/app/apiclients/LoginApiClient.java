package com.brave.registration.regist.app.apiclients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.db.entities.User;

import com.brave.registration.regist.app.response.UserResponse;
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

    public static LoginApiClient getInstance() {
        if ( instance == null) {
            instance = new LoginApiClient();
        }
        return instance;
    }

    private LoginApiClient() {

        this.ldUser = new MutableLiveData<>();

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
            try {
                if ( cancelRequest) {
                    return;
                }

                Response<UserResponse> response = login(user, password).execute();
                if ( response.code() == 200) {
                    UserResponse userResponse = response.body();
//                    User user = new User(userResponse);
//                    ldUser.postValue(user);
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

        private Call<UserResponse> login(String user, String password) {
            return RetrofitService.getBraveApi()
                    .login(user, password);
        }
    }
}
