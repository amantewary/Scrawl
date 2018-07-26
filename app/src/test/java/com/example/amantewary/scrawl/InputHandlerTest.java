package com.example.amantewary.scrawl;

import android.app.Activity;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class InputHandlerTest {

    InputHandler SUT;
    Activity activity;
    EditText testTitle;
    EditText testBody;
    EditText testUrl;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(AddNotesActivity.class);;
        SUT = new InputHandler(activity);
        testTitle = (EditText) activity.findViewById(R.id.et_title);
        testBody = (EditText) activity.findViewById(R.id.et_content);
        testUrl = (EditText) activity.findViewById(R.id.et_link);
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

    @Test
    public void inputCensor_emptyString_emptyStringReturned() {
        String result = SUT.inputCensor("");
        assertThat(result, is(""));
    }

    @Test
    public void inputCensor_singleCharacter_singleCharacterReturned() {
        String result = SUT.inputCensor("a");
        assertThat(result, is("a"));
    }

    @Test
    public void inputCensor_goodWordsString_emptyStringReturned() {
        String result = SUT.inputCensor("happy");
        assertThat(result, is("happy"));
    }

    @Test
    public void inputCensor_badWordsString_emptyStringReturned() {
        String result = SUT.inputCensor("bitch");
        assertThat(result, is("*****"));
    }

    @Test
    public void inputErrorHandler() {
//        SUT.inputErrorHandling(EditText);
    }
}