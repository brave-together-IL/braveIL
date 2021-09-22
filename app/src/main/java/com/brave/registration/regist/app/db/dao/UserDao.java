package com.brave.registration.regist.app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.db.entities.UserAndRole;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("Delete FROM user_table")
    void deleteAllUsers();

    @Transaction
    @Query("SELECT * FROM user_table")
    LiveData<List<UserAndRole>> getAllUsers();

}
