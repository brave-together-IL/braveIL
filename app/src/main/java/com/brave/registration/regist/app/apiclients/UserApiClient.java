package com.brave.registration.regist.app.apiclients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    private GetUserRunnable getUserRunnable;

    public static UserApiClient getInstance() {
        if ( instance == null) {
            instance = new UserApiClient();
        }
        return instance;
    }

    private UserApiClient() {
        this.ldUser = new MutableLiveData<>();
    }

    public LiveData<UserResponse> getLDUser() {
        return ldUser;
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
}
