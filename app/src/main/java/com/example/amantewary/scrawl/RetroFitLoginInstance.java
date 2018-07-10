package com.example.amantewary.scrawl;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitLoginInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        // Lazy initialization
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(AppURLs.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

