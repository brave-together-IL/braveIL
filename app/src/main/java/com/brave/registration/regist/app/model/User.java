package com.brave.registration.regist.app.model;

public class User {

    private int id;
    private String email;
    private String password;
    private String cellphone;
    private String first_name;
    private String lastName;

    public User(String email, String password, String cellphone, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.first_name = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLastName() {
        return lastName;
    }
}
