package com.example.amantewary.scrawl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppURLsTest {

    private AppURLs appURLs;
    @Before
    public void initClass(){
        appURLs = AppURLs.getInstance();
    }

    @Test
    public void getCorrectAppURL(){
        assertSame(appURLs.baseUrl, "https://web.cs.dal.ca/");
    }

}
