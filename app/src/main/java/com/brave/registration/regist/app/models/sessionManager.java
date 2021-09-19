package com.brave.registration.regist.app.models;
import android.util.Log;

import com.brave.registration.regist.app.models.User;
import com.brave.registration.regist.app.ui.login.auth.AuthResource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
public class sessionManager {
    private static final String TAG = "braveIL";

    // data
    private static MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if(cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public static void logOut() {
        Log.d(TAG, "logOut: logging out...");
        cachedUser.setValue(AuthResource.<User>logout());
    }


    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}
