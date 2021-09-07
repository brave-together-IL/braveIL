package com.brave.registration.regist.app.utils;

import com.brave.registration.regist.app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BraveApi {

    @GET("user/{user_ID}")
    Call<User> getUser(
            @Path("user_ID") String user_ID
    );

    @Headers("Content-type: application/json")
    @POST("user")
    Call<User> saveUser (
        @Body User user
    );
}
