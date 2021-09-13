package com.brave.registration.regist.app.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit.Builder retrofitBraveBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofitBrave = retrofitBraveBuilder.build();
    private static BraveApi braveApi = retrofitBrave.create(BraveApi.class);
    public static BraveApi getBraveApi() { return braveApi;};


    // Test api
    private static Retrofit.Builder retrofitBraveTestBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.LOCAL_TEST_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofitBraveTest = retrofitBraveTestBuilder.build();
    private static BraveApi braveTestApi = retrofitBraveTest.create(BraveApi.class);
    public static BraveApi getBraveTestApi() { return braveTestApi;};

}
