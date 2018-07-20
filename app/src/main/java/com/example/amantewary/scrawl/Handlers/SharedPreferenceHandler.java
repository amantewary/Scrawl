package com.example.amantewary.scrawl.Handlers;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.SharedPreferences.Editor;

public class SharedPreferenceHandler{


    public static SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences("spLabel", context.MODE_PRIVATE);
    }

    public static Editor getSharedPrefEditor(Context context){
        return getSharedPref(context).edit();
    }

    public static void clearPreferences(Context context){
        SharedPreferences.Editor editor = getSharedPrefEditor(context);
        editor.remove("spLabel");
        editor.apply();
    }
}
