package com.brave.registration.regist.app.mock;

import com.brave.registration.regist.app.models.User;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    private static MockDB instance;
    List<User> users;
    private static int id = 0;
    public static MockDB getInstance() {
        if ( instance == null) {
            instance = new MockDB();
        }
        return instance;
    }

    private MockDB() {
        users = new ArrayList<>();
        User superUser = new User(Integer.toString(++id), "superuser@brave.com", "1234512345", "", "Super User", "");
        addUser(superUser);
    }


    public User login(String username, String password) {
        for (User user : users) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public void addUser(User user) {
        this.users.add(user);
    }
}
