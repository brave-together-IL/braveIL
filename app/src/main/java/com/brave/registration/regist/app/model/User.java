package com.brave.registration.regist.app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

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


    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        password = in.readString();
        cellphone = in.readString();
        first_name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(cellphone);
        parcel.writeString(first_name);
        parcel.writeString(lastName);
    }
}
