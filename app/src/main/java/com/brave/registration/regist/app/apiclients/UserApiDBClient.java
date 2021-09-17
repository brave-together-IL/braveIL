package com.brave.registration.regist.app.apiclients;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brave.registration.regist.app.db.BraveDatabase;
import com.brave.registration.regist.app.db.dao.UserDao;
import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.utils.AppExecutors;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UserApiDBClient {
    public static final String TAG = UserApiDBClient.class.getSimpleName();
    UserDao userDao;
    private static UserApiDBClient instance;

    private MutableLiveData<User> ldUser;

    InsertUserRunnable insertUserRunnable;

    public static UserApiDBClient getInstance(Application application) {
        if (instance == null) {
            instance = new UserApiDBClient(application);
        }

        return instance;
    }

    private UserApiDBClient(Application application) {
        BraveDatabase db = BraveDatabase.getInstance(application);
        userDao = db.userDao();
        this.ldUser = new MutableLiveData<>();
    }

    public void insert(User user) {
        if (insertUserRunnable != null) {
            insertUserRunnable = null;
        }

        insertUserRunnable = new InsertUserRunnable(user);

        final Future userHandler = AppExecutors.getInstance().networkIO().submit(insertUserRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                userHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    private class InsertUserRunnable implements Runnable {
        private User user;
        boolean cancelRequest;

        public InsertUserRunnable(User user) {
            this.user = user;
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            userDao.insert(user);
//            Log.v(TAG, String.valueOf(userID));
        }
    }

    public LiveData<User> getLDUser() {
        return ldUser;
    }


}
