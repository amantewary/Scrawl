package com.example.amantewary.scrawl;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionManagerTest {

    private static final String KEY_NAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERID = "userid";
    private static final String Shared_Pref_Name = "Scrawl";
    private static final String IS_LOGIN = "Is_Login";
    int PRIVATE_MODE = 0;

    SessionManager sessionManager;
    Context context;
    SharedPreferences userPreferences;

    @Before
    public void init(){

        context = InstrumentationRegistry.getTargetContext();
        sessionManager = new SessionManager(context);
        userPreferences = context.getSharedPreferences(Shared_Pref_Name, PRIVATE_MODE);

    }

    @Test
    public void testIsUserLoggedIn(){
        assertEquals(sessionManager.checkLogin(),false);
    }


}
