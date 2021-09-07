package com.brave.registration.regist.app.services;

import android.util.Log;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.utils.BraveApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {

    public static void getUser(int userID) {
        BraveApi braveApi = RetrofitService.getBraveApi();

        Call<User> responseCall = braveApi.getUser(Integer.toString(userID));

        responseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("Tag", Integer.toString(response.code()));
                if ( response.code() == 200) {
                    User user = response.body();
                    Log.v("Tag", "the response " + user );
                } else {
                    Log.v("Tag", "Error " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
               t.printStackTrace();
            }
        });
    }

    public static void saveUser(User user) {
        BraveApi braveApi = RetrofitService.getBraveApi();

        Call<User> responseCall = braveApi.saveUser(user);

        responseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("Tag", Integer.toString(response.code()));
                if ( response.code() == 200) {
                    User user = response.body();
                    Log.v("Tag", "the response " + user );
                } else {
                    Log.v("Tag", "Error " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
