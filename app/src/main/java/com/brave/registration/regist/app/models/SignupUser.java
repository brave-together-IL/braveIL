package com.brave.registration.regist.app.models;

public class SignupUser {
    private String email;
    private String password;
    private String cellphone;
    private String first_name;
    private String last_name;

    public SignupUser(String email, String password, String cellphone, String first_Name, String last_Name) {
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.first_name = first_Name;
        this.last_name = last_Name;
    }
}
