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
    Integer userId;


    public UserClass(String email, String username, Integer userId) {
        this.email = email;
        this.username = username;
        this.userId = userId;
    }


    public String getError_msg() {
        return error_msg;
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

    public Integer getUserId() {
        return userId;
    }

}
