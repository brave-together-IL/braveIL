package com.brave.registration.regist.app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.brave.registration.regist.app.db.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("Delete FROM users")
    void deleteAllUsers();

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();
}
