package com.example.amantewary.scrawl;


/**
 * Todo: This is a Singleton class to store all the app URls.
 */

public class AppURLs {

    private static AppURLs ourInstance=new AppURLs();
    static String baseUrl;

    private AppURLs() {
        baseUrl = "https://web.cs.dal.ca/";
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public static AppURLs getInstance() {
        return ourInstance;
    }
}
