package com.example.amantewary.scrawl;

import android.util.Log;
import android.util.Patterns;

public class EmailPasswordValidation {

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        Log.e("Password length", (password.length() < 4 )+ " ");
        return password.length() < 4;
    }
}
