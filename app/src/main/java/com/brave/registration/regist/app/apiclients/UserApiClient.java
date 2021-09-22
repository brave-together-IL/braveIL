package com.brave.registration.regist.app.apiclients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.models.SignupUser;
import com.brave.registration.regist.app.response.UserResponse;
import com.brave.registration.regist.app.utils.AppExecutors;
import com.brave.registration.regist.app.utils.RetrofitService;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;


public class UserApiClient {
    private static UserApiClient instance;

    private MutableLiveData<UserResponse> ldUser;
    private MutableLiveData<UserResponse> ldSignupUser;

    private GetUserRunnable getUserRunnable;
    private SignupUserRunnable signupUserRunnable;

    public static UserApiClient getInstance() {
        if ( instance == null) {
            instance = new UserApiClient();
        }
        return instance;
    }

    private UserApiClient() {

        this.ldUser = new MutableLiveData<>();
        this.ldSignupUser = new MutableLiveData<>();
    }

    public LiveData<UserResponse> getLDUser() {
        return ldUser;
    }

    public LiveData<UserResponse> getLDSignupUser() {
        return ldSignupUser;
    }

    public void getUser(String id) {
        if ( getUserRunnable!=null) {
            getUserRunnable = null;
        }

        getUserRunnable = new UserApiClient.GetUserRunnable(id);

        final Future userHandler= AppExecutors.getInstance().networkIO().submit(getUserRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {

            @Override
            public void run() {
                userHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void signupUser(SignupUser user) {
        if ( signupUserRunnable!=null) {
            signupUserRunnable = null;
        }

        signupUserRunnable = new UserApiClient.SignupUserRunnable(user);

        final Future signupUserHandler= AppExecutors.getInstance().networkIO().submit(signupUserRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {

            @Override
            public void run() {
                signupUserHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }
    private class GetUserRunnable implements Runnable {

        private String id;
        boolean cancelRequest;

        public GetUserRunnable(String id) {
            this.id = id;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                if ( cancelRequest) {
                    return;
                }

                Response<UserResponse> response = getUser(id).execute();
                if ( response.code() == 200) {
                    UserResponse user = response.body();
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

        private Call<UserResponse> getUser(String id) {
            return RetrofitService.getBraveApi()
                    .getUser(id);
        }


    }

    private class SignupUserRunnable implements Runnable {

        private SignupUser user;
        boolean cancelRequest;

        public SignupUserRunnable(SignupUser user) {
            this.user = user;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                if ( cancelRequest) {
                    return;
                }

                Response<UserResponse> response = signupUser(user).execute();
                if ( response.code() == 200) {
                    UserResponse user = response.body();
                    ldSignupUser.postValue(user);
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    ldSignupUser.postValue(null);
                }
            } catch ( IOException e) {
                Log.v("TOKEN_API", "failed getting token");
                e.printStackTrace();
                ldSignupUser.postValue(null);
            }
        }

        private Call<UserResponse> signupUser(SignupUser user) {
            return RetrofitService.getBraveApi()
                    .saveUser(user);
        }


    }
}
