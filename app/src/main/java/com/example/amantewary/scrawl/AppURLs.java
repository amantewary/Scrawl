package com.example.amantewary.scrawl;


/**
 * Todo: This is a Singleton class to store all the app URls. Make sure you guys add Your URLs here
 */

public class AppURLs {

    private static final AppURLs ourInstance = new AppURLs();
    static String baseUrl;

    private AppURLs() {
        baseUrl = "https://web.cs.dal.ca/";
    }

    public static AppURLs getInstance() {
        return ourInstance;
    }
}
