package com.brave.registration.regist.app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.db.entities.UserAndRole;
import com.brave.registration.regist.app.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<UserAndRole>> allUsers;


    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
        allUsers = repository.getAllusers();
    }

    public void insert(User user) { repository.insert(user);}
    public void update(User user) { repository.update(user);}
    public void delete(User user) { repository.delete(user);}
    public LiveData<List<UserAndRole>> getAllUsers() { return allUsers;}


    public LiveData<UserAndRole> login(String email, String phone) {
        return repository.login(email, phone);
    }
}

