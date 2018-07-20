package com.example.amantewary.scrawl.Handlers;


import com.google.gson.annotations.SerializedName;

public class UserClass {


    @SerializedName("password")
    String password;
    @SerializedName("email")
    String email;
    @SerializedName("username")
    String username;
    @SerializedName("error")
    String error;
    @SerializedName("error_msg")
    String error_msg;

    @SerializedName("userId")
    String userId;


    public UserClass(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public UserClass(String email, String username, String userId) {
        this.email = email;
        this.username = username;
        this.userId = userId;
    }


    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;


    }
}
