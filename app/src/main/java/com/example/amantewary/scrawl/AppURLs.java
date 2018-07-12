package com.example.amantewary.scrawl;


/**
 * Todo: This is a Singleton class to store all the app URls. Make sure you guys add Your URLs here
 */

public class AppURLs {

    private static final AppURLs ourInstance = new AppURLs();
    static String loginURL, registerURL, baseUrl;

    private AppURLs() {
        baseUrl = "https://web.cs.dal.ca/";
        loginURL = "https://web.cs.dal.ca/~kamath/QA_Devint/login.php";
        registerURL = "http://web.cs.dal.ca/~kamath/QA_Devint/register.php";
    }


    public static AppURLs getInstance() {
        return ourInstance;
    }
}
