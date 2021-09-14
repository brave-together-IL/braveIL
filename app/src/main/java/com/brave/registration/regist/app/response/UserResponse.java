package com.brave.registration.regist.app.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {


    private String id;
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String cellphone;

    private String password;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getPassword() { return password;}
    @Override
    public String toString() {
        return "UserResponse{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
