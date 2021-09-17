package com.brave.registration.regist.app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllusers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.insert(user);
    }

    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

}

