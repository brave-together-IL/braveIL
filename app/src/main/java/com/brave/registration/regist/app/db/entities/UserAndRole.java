package com.brave.registration.regist.app.db.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndRole {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "roleID",
            entityColumn = "id"
    )
    public Role role;

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserAndRole{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
