package com.brave.registration.regist.app.services;

import com.brave.registration.regist.app.utils.BraveApi;
import com.brave.registration.regist.app.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static BraveApi braveApi = retrofit.create(BraveApi.class);

    public static BraveApi getBraveApi() {
        return braveApi;
    }
}
