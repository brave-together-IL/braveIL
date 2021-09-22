package com.brave.registration.regist.app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.brave.registration.regist.app.db.entities.Role;

import java.util.List;

@Dao
public interface RoleDao {

    @Insert
    long insert(Role role);

    @Query("SELECT * FROM role_table")
    LiveData<List<Role>> getAllRoles();

}
