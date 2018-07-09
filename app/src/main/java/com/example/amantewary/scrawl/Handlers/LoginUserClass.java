package com.example.amantewary.scrawl.Handlers;

public class LoginUserClass {


    boolean error;
    String username;
    String email;

    public LoginUserClass(boolean error, String username, String email) {
        this.error = error;
        this.username = username;
        this.email = email;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
