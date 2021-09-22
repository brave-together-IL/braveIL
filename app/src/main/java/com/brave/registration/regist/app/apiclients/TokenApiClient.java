package com.brave.registration.regist.app.apiclients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.MainActivity;
import com.brave.registration.regist.app.response.TokenResponse;
import com.brave.registration.regist.app.utils.API_MODE;
import com.brave.registration.regist.app.utils.AppExecutors;
import com.brave.registration.regist.app.utils.RetrofitService;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class TokenApiClient {
    private static TokenApiClient instance;

    private MutableLiveData<String> ldToken;

    private GetTokenRunnable getTokenRunnable;

    public static TokenApiClient getInstance() {
        if ( instance == null) {
            instance = new TokenApiClient();
        }
        return instance;
    }

    private TokenApiClient() {
        this.ldToken = new MutableLiveData<>();
    }

    public LiveData<String> getLDToken() {
        return ldToken;
    }

    public void getToken(String auth) {
        if ( getTokenRunnable!=null) {
            getTokenRunnable = null;
        }

        getTokenRunnable = new GetTokenRunnable(auth);

        final Future tokenHandler= AppExecutors.getInstance().networkIO().submit(getTokenRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {

            @Override
            public void run() {
                tokenHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
     }

     private class GetTokenRunnable implements Runnable {

        private String auth;
        boolean cancelRequest;

        public GetTokenRunnable(String auth) {
            this.auth = auth;
            this.cancelRequest = false;
        }

         @Override
         public void run() {
            try {
                if ( cancelRequest) {
                    return;
                }

                Response response = getToken(auth).execute();
                if ( response.code() == 200) {
                    String token = response.body().toString();
                    ldToken.postValue(token);
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    ldToken.postValue(null);
                }
            } catch ( IOException e) {
                Log.v("TOKEN_API", "failed getting token");
                e.printStackTrace();
                ldToken.postValue(null);
            }
         }

         private Call<TokenResponse> getToken(String auth) {
            return RetrofitService.getBraveApi()
                    .getToken(auth);
         }
     }
}
