package com.brave.registration.regist.app.response;

public class TokenResponse {

    private String token;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
