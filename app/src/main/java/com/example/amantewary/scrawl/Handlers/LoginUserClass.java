package com.example.amantewary.scrawl.Handlers;


import com.google.gson.annotations.SerializedName;

public class LoginUserClass {


    @SerializedName("password")
    String password;
    @SerializedName("email")
    String email;
    @SerializedName("username")
    String username;
    @SerializedName("error")
    String error;



    public LoginUserClass(String email, String password) {
        this.password = password;
        this.email = email;
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
}
