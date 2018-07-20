package com.example.amantewary.scrawl.Handlers;


import com.google.gson.annotations.SerializedName;

public class LoginUserClass {

    @SerializedName("id")
    Integer id;
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


    public LoginUserClass(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public LoginUserClass(String email, String password, Integer id) {
        this.password = password;
        this.email = email;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
