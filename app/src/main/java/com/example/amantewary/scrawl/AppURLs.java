package com.example.amantewary.scrawl;


/**
 * Todo: This is a Singleton class to store all the app URls. Make sure you guys add Your URLs here
 *
 */



public class AppURLs {

    static String loginURL, registerURL, labelApiURL, noteApiURL;
    private static final AppURLs ourInstance = new AppURLs();

    public static AppURLs getInstance() {
        return ourInstance;
    }

    private AppURLs() {
        loginURL = "https://web.cs.dal.ca/~kamath/QA_Devint/login.php";
        registerURL = "http://web.cs.dal.ca/~kamath/QA_Devint/register.php";
        labelApiURL = "https://web.cs.dal.ca/~kamath/QA_Devint/NoteApi/v1/label/";
        noteApiURL = "https://web.cs.dal.ca/~kamath/QA_Devint/NoteApi/v1/notes/";
    }
}
