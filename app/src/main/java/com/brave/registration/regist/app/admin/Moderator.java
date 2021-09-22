package com.brave.registration.regist.app.admin;

public class Moderator {

    String username;
    String password;
    public Moderator(String username, String password)
    {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}