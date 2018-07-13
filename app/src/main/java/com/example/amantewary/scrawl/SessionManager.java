package com.example.amantewary.scrawl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * This class is created to maintain the session of user. Android doesn't provide a built-in solution. Hence we are
 * using Shared Preferences.
 *
 * Code is inspired from: https://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/
 * **/

public class SessionManager {


    SharedPreferences pref;


    SharedPreferences.Editor editor;


    Context context;


    int PRIVATE_MODE = 0;


    private static final String Shared_Pref_Name = "Scrawl";


    private static final String IS_LOGIN = "Is_Login";

    public static final String KEY_NAME = "username";

    public static final String KEY_EMAIL = "email";

    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(Shared_Pref_Name, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }


    public boolean checkLogin(){
        if(!this.isLoggedIn()){
          return false;
        }else{
            return true;
        }

    }



    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}

