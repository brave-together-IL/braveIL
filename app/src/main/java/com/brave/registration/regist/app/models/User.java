package com.brave.registration.regist.app.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.brave.registration.regist.app.response.UserResponse;

public class User implements Parcelable {

    private String id;
    private String email;
    private String password;
    private String cellphone;
    private String firstName;
    private String lastName;

    public User(UserResponse userResponse) {
        this(userResponse.getId(), userResponse.getEmail(), userResponse.getPassword(), userResponse.getCellphone(), userResponse.getFirstName(), userResponse.getLastName());
    }

    public User(String id, String email, String password, String cellphone, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected User(Parcel in) {
        id = in.readString();
        email = in.readString();
        password = in.readString();
        cellphone = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(cellphone);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }
}
