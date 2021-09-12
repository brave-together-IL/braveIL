package com.brave.registration.regist.app.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.services.RetrofitService;
import com.brave.registration.regist.app.utils.AppExecutors;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClient {

    private static UserApiClient instance;
    // LiveData
    private MutableLiveData<User> mUser;

    private RetrieveUserRunnable retrieveUserRunnable;
    private RegisterUserRunnable registerUserRunnable;

    public static UserApiClient getInstance() {
        if ( instance == null ) {
            instance = new UserApiClient();
        }

        return instance;
    }

    private UserApiClient() {
        mUser = new MutableLiveData<>();
    }

    public LiveData<User> getUser() {
        return mUser;
    }


    public void getUser(String userID) {
        if ( retrieveUserRunnable!=null) {
            retrieveUserRunnable = null;
        }

        retrieveUserRunnable = new RetrieveUserRunnable(userID);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveUserRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the retrofit call
                myHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);
    }

    public void registerUser(User user) {
        if ( registerUserRunnable!=null) {
            registerUserRunnable = null;
        }

        registerUserRunnable = new RegisterUserRunnable(user);

        final Future registerUserHandler = AppExecutors.getInstance().networkIO().submit(registerUserRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                registerUserHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    private class RegisterUserRunnable implements  Runnable {
        private User user;
        boolean cancelRequest;

        public RegisterUserRunnable(User user) {
            this.user = user;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                if (cancelRequest) {
                    return;
                }

                Response response = RegisterUser(user).execute();
            } catch (IOException e) {
                e.printStackTrace();
                mUser.postValue(null);
            }
        }

        private Call<User> RegisterUser(User user) {
            return RetrofitService.getBraveApi().saveUser(user);
        }

        private void cancelRequest() {
            Log.v( "Tag", "Cancelling register user Request");
            cancelRequest = true;
        }

    }

    private class RetrieveUserRunnable implements Runnable {
        private String userID;
        boolean cancelRequest;

        public RetrieveUserRunnable(String userID) {
            this.userID = userID;
            this.cancelRequest = false;
        }
        @Override
        public void run() {
            try {
                if (cancelRequest) {
                    return;
                }

                Response response = getUser(userID).execute();
                Log.v("Response", response.toString());
            } catch (IOException e) {
                e.printStackTrace();
                mUser.postValue(null);
            }
        }

        private Call<User> getUser(String userID) {
            return RetrofitService.getBraveApi().getUser(userID);
        }

        private void cancelRequest() {
            Log.v( "Tag", "Cancelling Request");
            cancelRequest = true;
        }
    }

}
