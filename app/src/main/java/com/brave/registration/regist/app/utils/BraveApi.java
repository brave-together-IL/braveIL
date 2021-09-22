package com.brave.registration.regist.app.utils;

import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.models.SignupUser;
import com.brave.registration.regist.app.response.TokenResponse;
import com.brave.registration.regist.app.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BraveApi {


    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{user_ID}")
    Call<UserResponse> getUser(
            @Path("user_ID") String user_ID
    );

    @Headers("Content-type: application/json")
    @POST("user")
    Call<UserResponse> saveUser (
            @Body SignupUser user
    );

    @Headers("Content-type: application/json")
    @GET("token")
    Call<TokenResponse> getToken(@Header("Authorization") String auth);

    //TODO
    @GET("user/login")
    Call<UserResponse> login(String user, String password);
}
