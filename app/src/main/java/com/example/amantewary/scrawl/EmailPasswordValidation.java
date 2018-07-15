package com.example.amantewary.scrawl;

import android.util.Patterns;

public class EmailPasswordValidation {

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {

        return password.length() < 4;
    }
}
