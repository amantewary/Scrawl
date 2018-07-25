package com.example.amantewary.scrawl;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class InputHandlerTest {

    InputHandler SUT;
    Context context;

    @Before
    public void setUp() throws Exception {
        SUT = new InputHandler(context);
    }

    @Test
    public void inputValidator_emptyString_falseReturned() {
        boolean result = SUT.inputValidator("","","");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyTitle_falseReturned() {
        boolean result = SUT.inputValidator("","BODY","www.test.com");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyBody_falseReturned() {
        boolean result = SUT.inputValidator("TITLE","","www.test.com");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyUrl_trueReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","");
        assertThat(result, is(true));
    }

    @Test
    public void inputValidator_wrongUrlPattern_falseReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","URL");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_correctInput_trueReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","www.test.com");
        assertThat(result, is(true));
    }

//    @Test
//    public void inputCensor() {
//        String result = SUT.inputCensor("a");
//        assertThat(result, is("a"));
//    }

}