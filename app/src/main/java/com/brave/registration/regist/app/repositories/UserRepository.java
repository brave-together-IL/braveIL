package com.brave.registration.regist.app.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.apiclients.UserApiDBClient;
import com.brave.registration.regist.app.db.BraveDatabase;
import com.brave.registration.regist.app.db.dao.UserDao;
import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.db.entities.UserAndRole;

import java.util.List;

public class UserRepository {
    private static UserRepository instance;
//
//    private UserApiDBClient userApiDBClient;

    UserDao userDao;
    LiveData<List<UserAndRole>> allUsers;

    private UserRepository(Application application) {
        BraveDatabase db = BraveDatabase.getInstance(application);
        this.userDao = db.userDao();
        allUsers = userDao.getAllUsers();
//        userApiDBClient = userApiDBClient.getInstance(application);
    }

    public static UserRepository getInstance(Application application) {
        if ( instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
//        userApiDBClient.insert(user);
    }

//    public LiveData<User> getLDUser() { return userApiDBClient.getLDUser();}

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public LiveData<List<UserAndRole>> getAllusers() {
        return allUsers;
    }

    public LiveData<UserAndRole> login(String email, String phone) {
        return userDao.findUser(email, phone);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
