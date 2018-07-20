package com.example.amantewary.scrawl;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

//SINGLETON
public class LabelLoader {
    private static final LabelLoader ourInstance = new LabelLoader();
    private ArrayList<String> labels;

    private LabelLoader() {
    }

    public static LabelLoader getInstance() {
        return ourInstance;
    }

    public void saveLabel(Context context, ArrayList<String> labels) {
        this.labels = labels;
        SharedPreferences sharedPreferences = context.getSharedPreferences("spLabel", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(labels);
        editor.putString("label list", json);
        editor.apply();
    }

    public ArrayList<String> loadLabel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("spLabel", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("label list", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        labels = gson.fromJson(json, type);
        if (labels == null) {
            labels = new ArrayList<>();
        }
        return labels;
    }
}
