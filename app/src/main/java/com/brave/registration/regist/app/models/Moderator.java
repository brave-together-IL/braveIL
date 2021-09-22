package com.brave.registration.regist.app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Moderator implements Parcelable {

    private String username;
    private String password;

    public Moderator(String username, String password) {
        this.username = username;
        this.password = password;

    }

    protected Moderator(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<Moderator> CREATOR = new Creator<Moderator>() {
        @Override
        public Moderator createFromParcel(Parcel in) {
            return new Moderator(in);
        }

        @Override
        public Moderator[] newArray(int size) {
            return new Moderator[size];
        }
    };

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
      }
}
